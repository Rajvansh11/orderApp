package customerService.customerService.service;

import customerService.customerService.dto.*;
import customerService.customerService.entity.Address;
import customerService.customerService.entity.Customers;
import customerService.customerService.entity.OrderItem;
import customerService.customerService.entity.Orders;
import customerService.customerService.exception.CustomException;
import customerService.customerService.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

/**
 * The customer should have access to the following services
 * 1.>sign up for the website
 * 2.>update his/her details & view them
 * 3.>view all products available to buy
 * 4.>place order for items
 * 5.>track order items
 * 6.>view order history
 * 7.>cancel an order item
 * 8.>cancel an order
 */
@Service
public class CustomerService
{
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderItemLocationRepository orderItemLocationRepository;
    private final CustomerRepository customerRepository;
    private final KafkaTemplate<Long,OrderKafkaDto> kafkaTemplate;
    @Value("${kafka.topic.customer-placed-order}")
    private String customerOrderTopic;
    CustomerService(OrderRepository or, OrderItemRepository oir, ProductRepository pr, OrderItemLocationRepository oilr, CustomerRepository cr,KafkaTemplate<Long,OrderKafkaDto>kt_) {
        orderRepository = or;
        orderItemRepository = oir;
        productRepository = pr;
        orderItemLocationRepository = oilr;
        customerRepository = cr;
        kafkaTemplate=kt_;
    }
    private static  final Logger log= LoggerFactory.getLogger(CustomerService.class);
    public ResponseEntity<String> addCustomer(AddCustomerDto addCustomerDto) {
        try {
            if (customerRepository.findByEmailId(addCustomerDto.getEmailId()).isPresent()) {
                throw new CustomException("Customer email already exists");
            }
            if (customerRepository.findByPhoneNumber(addCustomerDto.getPhoneNumber()).isPresent()) {
                throw new CustomException("Customer phone no already exists");
            }
//            if (customerRepository.findByFirstNameLastNameAndAddress(addCustomerDto.getFirstName(), addCustomerDto.getLastName(), addCustomerDto.getAddress()).isPresent()) {
//                throw new CustomException("Customer with same name and address is already registered");
//            }
            Customers customer=new Customers(addCustomerDto.getFirstName(),addCustomerDto.getLastName(),addCustomerDto.getAddress(), addCustomerDto.getPhoneNumber(),addCustomerDto.getEmailId());
            Customers addedCustomer=customerRepository.save(customer);
            log.info("Customer with customerId -> {} was added to the db",addedCustomer.getId());
            return new ResponseEntity<>("Customer saved successfully", HttpStatus.OK);
        } catch (CustomException ce) {
            log.error(ce.getMessage());
            return new ResponseEntity<>(ce.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error("new Customer could not be added to the db");
            return new ResponseEntity<>("Something went wrong while saving the customer, please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateCustomer(Long id, CustomerUpdateRequestDto customerUpdateRequestDto) {
        try {
            //fetching the saved customer from the db
            Customers customer = customerRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Customer you were looking to update could not be found"));
            Address address = customer.getAddress();
            //checking to see what fields to update
            if (customerUpdateRequestDto.getFirstName() != null)
                customer.setFirstName(customerUpdateRequestDto.getFirstName());

            if (customerUpdateRequestDto.getLastName() != null)
                customer.setLastName(customerUpdateRequestDto.getLastName());

            if (customerUpdateRequestDto.getEmailId() != null)
                customer.setEmailId(customerUpdateRequestDto.getEmailId());

            if (customerUpdateRequestDto.getPhoneNumber() != null)
                customer.setPhoneNumber(customerUpdateRequestDto.getPhoneNumber());

            if (customerUpdateRequestDto.getFlatNo() != null)
                address.setFlatNo(customerUpdateRequestDto.getFlatNo());

            if (customerUpdateRequestDto.getCity() != null)
                address.setCity(customerUpdateRequestDto.getCity());

            if (customerUpdateRequestDto.getState() != null)
                address.setState(customerUpdateRequestDto.getState());

            if (customerUpdateRequestDto.getZip() != null)
                address.setZip(customerUpdateRequestDto.getZip());

            if (customerUpdateRequestDto.getStreetOrSocietyOrColony() != null)
                address.setStreetOrSocietyOrColony(customerUpdateRequestDto.getStreetOrSocietyOrColony());
            customerRepository.save(customer);
            log.info("Customer details updated successfully");
            return new ResponseEntity<>("Customer updated Successfully", HttpStatus.OK);
        } catch (CustomException ce) {
            log.error(ce.getMessage());
            return new ResponseEntity<>(ce.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Something went wrong while updating the customer");
            return new ResponseEntity<>("Something went wrong while updating the customer,please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<StoreListedProducts> getAllProductsCustomerCanBuy()
    {
            return productRepository.findAll().stream().filter(prod -> prod.getQuantity() > 0).map(StoreListedProducts::mapProductToStoreListedProduct).toList();
    }

    public ResponseEntity<Object> getListOfAllProductsAvailableToBuy() {
        try {
            return new ResponseEntity<>(getAllProductsCustomerCanBuy(), HttpStatus.FOUND);
        } catch (Exception e) {
            log.error("Something went wrong while fetching products");
            return new ResponseEntity<>("Something went wrong while fetching products,please try again after sometime",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<CustomerOrderProcessingDto> makeNewOrder(OrderItemsDto orderItemsDto)
    {
        List<SingleOrderItemDto>items=orderItemsDto.getOrderItems();
        Customers customer=customerRepository.findById(orderItemsDto.getCustomerId()).orElseThrow(()->new CustomException("Customer Not Found"));
        /*
        validate the order Items:In case the quantity ordered is more than the quantity present in the
        inventory then we will cut down the items to what we can sell and show the same to the end user.
        */
        try
        {
            items
                    .forEach(item->
                    {
                        int maxQuantity=productRepository.findById(item.getProductId()).orElseThrow(()->new CustomException("product not available in catalogue")).getQuantity();
                        if(item.getQuantity()>maxQuantity)
                        {
                            item.setQuantity(maxQuantity);
                        }
                    });
            List<OrderItemKafkaDto>orderItemKafkaDtoList=items.stream().map(item->new OrderItemKafkaDto(item.getQuantity(),"Pune-MH",item.getProductId())).toList();
            UUID uuid=UUID.randomUUID();
            OrderKafkaDto orderKafkaDto=new OrderKafkaDto(customer.getId(),orderItemKafkaDtoList,uuid,OrderStatus.PENDING);
            log.info("...sending orderKafkaDto -> {} to kafka topic",orderKafkaDto);
            kafkaTemplate.send(customerOrderTopic,customer.getId(),orderKafkaDto);
            CustomerOrderProcessingDto customerOrderProcessingDto=new CustomerOrderProcessingDto(OrderStatus.PENDING,uuid);
            log.info("order sent for processing");
            return new ResponseEntity<>(customerOrderProcessingDto,HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            log.error("Something went wrong while placing the order ",e);
            throw new CustomException("Something went wrong while placing the order, please try again later");
        }
    }

    public ResponseEntity<Object>getOrderFromUUID(UUID uuid,OrderStatus status)
    {
        try
        {
            Orders order=orderRepository.findByUUIDAndStatus(uuid,status).orElseThrow(()->new CustomException("Order with the given UUID and status does not exist"));
            log.info("Order with uuid -> {} and status -> {} was found in the db",uuid,status);
            return new ResponseEntity<>("Order with the given uuid and status was found in the db",HttpStatus.FOUND);
        }
        catch(CustomException ce)
        {
            log.error("order with uuid -> {}  and status -> {} was not found in the db",uuid,status);
            return new ResponseEntity<>(ce.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}