package orderService.orderService.service;

import jakarta.transaction.Transactional;
import orderService.orderService.dto.InventoryKafkaDto;
import orderService.orderService.dto.OrderItemKafkaDto;
import orderService.orderService.dto.OrderStatus;
import orderService.orderService.entity.*;
import orderService.orderService.exception.CustomException;
import orderService.orderService.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderDbTransactionsService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemLocationRepository orderItemLocationRepository;
    private final KafkaTemplate<Long, InventoryKafkaDto>kafkaInventoryTemplate;
    @Value("${kafka.topic.order-inventory-update}")
    private String inventoryKafkaTopic;
    public OrderDbTransactionsService(CustomerRepository customerRepository_, OrderRepository orderRepository_, ProductRepository productRepository_, OrderItemRepository orderItemRepository_, OrderItemLocationRepository orderItemLocationRepository_,KafkaTemplate<Long, InventoryKafkaDto>kafkaInventoryTemplate_) {
        customerRepository = customerRepository_;
        orderRepository = orderRepository_;
        productRepository = productRepository_;
        orderItemRepository = orderItemRepository_;
        orderItemLocationRepository = orderItemLocationRepository_;
        kafkaInventoryTemplate=kafkaInventoryTemplate_;
    }

    private static Logger log= LoggerFactory.getLogger(OrderDbTransactionsService.class);
    @Transactional
    public void saveOrderIntoDb(Customers customer, UUID uuid, List<OrderItemKafkaDto> items)
    {
        Orders o=new Orders();
        o.setStatus(OrderStatus.PENDING);
        Orders savedOrder = orderRepository.save(o);

        for (OrderItemKafkaDto orderItemKafkaDto : items) {
            Products product = productRepository.findById(orderItemKafkaDto.getProductId()).orElseThrow(() -> new CustomException("The ordered product could not be found "));
            log.error("The product with the id->{} could not be found", orderItemKafkaDto.getProductId());
            int quantity = orderItemKafkaDto.getQuantity();
            OrderItem orderItem = new OrderItem(quantity, savedOrder, product, false);
            OrderItem savedOrderItem = orderItemRepository.save(orderItem);

            OrderItemLocation oil = new OrderItemLocation(savedOrderItem, "Pune-MH");
            orderItemLocationRepository.save(oil);
            //TODO:send location inventory update via kafka topic and decrease stock
            InventoryKafkaDto inventoryKafkaDto=new InventoryKafkaDto(product.getId(),"Pune",orderItem.getQuantity());
            kafkaInventoryTemplate.send(inventoryKafkaTopic,product.getId(),inventoryKafkaDto);
        }
        savedOrder.setUuid(uuid);
        savedOrder.setCustomer(customer);
        savedOrder.setNoOfItems(items.size());
        savedOrder.setStatus(OrderStatus.PLACED);
        log.info("order with uuid -> {} and customerId -> {} placed",uuid,customer.getId());
    }

}