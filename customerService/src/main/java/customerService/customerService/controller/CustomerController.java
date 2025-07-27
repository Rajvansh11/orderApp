package customerService.customerService.controller;

import customerService.customerService.dto.AddCustomerDto;
import customerService.customerService.dto.CustomerOrderProcessingDto;
import customerService.customerService.dto.OrderItemsDto;
import customerService.customerService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getAllProductstoBuy")
    public ResponseEntity<Object>getAllProductsCustomerCanBuy()
    {
        return customerService.getListOfAllProductsAvailableToBuy();
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<CustomerOrderProcessingDto>placeOrder(@RequestBody OrderItemsDto orderItemsDto)
    {
        return customerService.makeNewOrder(orderItemsDto);
    }
}