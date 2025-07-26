package customerService.customerService.service;

import customerService.customerService.dto.CustomerUpdateRequestDto;
import customerService.customerService.dto.StoreListedProducts;
import customerService.customerService.entity.Address;
import customerService.customerService.entity.Customers;
import customerService.customerService.entity.OrderItem;
import customerService.customerService.exception.CustomException;
import customerService.customerService.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * the customer should have access to the following services
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

    CustomerService(OrderRepository or, OrderItemRepository oir, ProductRepository pr, OrderItemLocationRepository oilr, CustomerRepository cr) {
        orderRepository = or;
        orderItemRepository = oir;
        productRepository = pr;
        orderItemLocationRepository = oilr;
        customerRepository = cr;
    }

    public ResponseEntity<String> addCustomer(Customers customer) {
        try {
            if (customerRepository.findByEmailId(customer.getEmailId()).isPresent()) {
                throw new CustomException("Customer email already exists");
            }
            if (customerRepository.findByPhoneNumber(customer.getPhoneNumber()).isPresent()) {
                throw new CustomException("Customer phone no already exists");
            }
            if (customerRepository.findByFirstNameLastNameAndAddress(customer.getFirstName(), customer.getLastName(), customer.getAddress()).isPresent()) {
                throw new CustomException("Customer with same name and address is already registered");
            }
            customerRepository.save(customer);
            return new ResponseEntity<>("Customer saved successfully", HttpStatus.OK);
        } catch (CustomException ce) {
            return new ResponseEntity<>(ce.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
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
            return new ResponseEntity<>("Customer updated Successfully", HttpStatus.OK);
        } catch (CustomException ce) {
            return new ResponseEntity<>(ce.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
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
            return new ResponseEntity<>("Something went wrong while fetching products,please try again after sometime",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> makeNewOrder(List<OrderItem>orderItems)
    {
        //validate the order Items:
        try
        {
            Map<Long, OrderItem> invalidItemsQuantityByProductId = orderItems.stream()
                    .filter(item -> item.getQuantity() > item.getProduct().getQuantity())
                    .collect(Collectors.toMap(
                            item -> item.getProduct().getId(),
                            item -> item
                    ));

        }
        catch()
        {

        }
    }

    public boolean validateOrderItemQuantity(OrderItem orderItem)
    {
        return orderItem.getQuantity()<=orderItem.getProduct().getQuantity();
    }
}