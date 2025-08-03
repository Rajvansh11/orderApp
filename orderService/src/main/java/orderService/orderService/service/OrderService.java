package orderService.orderService.service;

import orderService.orderService.entity.*;
import orderService.orderService.dto.OrderItemKafkaDto;
import orderService.orderService.dto.OrderKafkaDto;
import orderService.orderService.exception.CustomException;
import orderService.orderService.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemLocationRepository orderItemLocationRepository;
    private final OrderDbTransactionsService orderDbTransactionsService;

    public OrderService(CustomerRepository customerRepository_, OrderRepository orderRepository_, ProductRepository productRepository_, OrderItemRepository orderItemRepository_, OrderItemLocationRepository orderItemLocationRepository_, OrderDbTransactionsService orderDbTransactionsService_) {
        customerRepository = customerRepository_;
        orderRepository = orderRepository_;
        productRepository = productRepository_;
        orderItemRepository = orderItemRepository_;
        orderItemLocationRepository = orderItemLocationRepository_;
        orderDbTransactionsService = orderDbTransactionsService_;
    }

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    //    @KafkaListener(topics = "${kafka.topic.customer-placed-order}", groupId = "customer-placed-order-service")
//    @KafkaListener(topics = "${kafka.topic.customer-placed-order}", groupId = "${spring.kafka.consumer.group-id}")
    @KafkaListener(topics = "${kafka.topic.customer-placed-order}", groupId = "order-service-group")
    public void handleCustomerOrder(@Payload OrderKafkaDto orderKafkaDto,
            @Header(KafkaHeaders.ACKNOWLEDGMENT) Acknowledgment ack) {
        log.info("received the order payload {}", orderKafkaDto);
        List<OrderItemKafkaDto> items = orderKafkaDto.getOrderItemsList();

        log.info("order items received -> {}", items);

        Customers customer = customerRepository.findById(orderKafkaDto.getCustomerId())
                .orElseThrow(() -> {
                    log.error("The customer with the id -> {} could not be found", orderKafkaDto.getCustomerId());
                    return new CustomException("Customer not found");
                });

        UUID uuid = orderKafkaDto.getUuid();
        orderDbTransactionsService.saveOrderIntoDb(customer, uuid, items);

        ack.acknowledge();
    }
}