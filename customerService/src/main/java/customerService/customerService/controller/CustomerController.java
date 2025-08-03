package customerService.customerService.controller;

import customerService.customerService.dto.AddCustomerDto;
import customerService.customerService.dto.CustomerOrderProcessingDto;
import customerService.customerService.dto.OrderItemsDto;
import customerService.customerService.dto.OrderStatus;
import customerService.customerService.entity.Customers;
import customerService.customerService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("app/customer")
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity<String>addCustomer(@RequestBody AddCustomerDto addCustomerDto)
    {
        return customerService.addCustomer(addCustomerDto);
    }

    @GetMapping("/getAllProductsToBuy")
    public ResponseEntity<Object>getAllProductsCustomerCanBuy()
    {
        return customerService.getListOfAllProductsAvailableToBuy();
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<Object>placeOrder(@RequestBody OrderItemsDto orderItemsDto)
    {
        return customerService.makeNewOrder(orderItemsDto);
    }

    @GetMapping("/order-status/{uuid}/{status}")
    public ResponseEntity<String> getStatusOfOrderFromUUID(@PathVariable("uuid") UUID uuid, @PathVariable("status") String statusString)
    {
        OrderStatus status;
        try {
            status = OrderStatus.valueOf(statusString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid order status: " + statusString);
        }
        return customerService.getOrderFromUUID(uuid, status);
    }

    @GetMapping("/getCustomerDetails")
    public ResponseEntity<Object>getCustomer(@RequestParam("id")long id )
    {
        return customerService.getCustomerDetails(id);
    }



}