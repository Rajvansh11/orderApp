package customerService.customerService.service;

import customerService.customerService.entity.*;
import customerService.customerService.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class populateDbService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    OrderItemLocationRepository orderItemLocationRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateDb() throws Exception {
        Address a1=new Address("P44","TelcoColony","Jamshedpur","Jharkhand","831004");
        Address a2=new Address("M44","TwinCities","Jamshedpur","Jharkhand","831005");
        Address a3=new Address("305","Coliving Wakad","Pune","Maharashtra","411033");

        Customers c1=new Customers("Priyank","Rajvansh",a1,"90062167895","abc.xyz@gmail.com");
        Customers c2=new Customers("Parth","Parmar",a2,"9006216891","xyz.abc@gmail.com");
        Customers c3=new Customers("Akshat","Tripathi",a3,"9006216781","pqr.mno@gmail.com");

        customerRepository.save(c1);
        customerRepository.save(c2);
        customerRepository.save(c3);

        Products p1=new Products("medium","red","polo-shirt-med-red",100,20,30,"Red Polo Shirt");
        Products p2=new Products("large","red","polo-shirt-large-red",45,45,100,"Red Polo Shirt");
        Products p3=new Products("small","green","tracksuit-small-green",15,18,60,"Track Suit Green");
        Products p4=new Products("large","violet","large-trousers-blue",20,30,95,"Blue Trousers");
        Products p5=new Products("medium","yellow","medium-shirt-yellow",29,120,200,"Yellow Shirt");

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);
        productRepository.save(p5);

        Inventory i1=new Inventory(p1,"Pune",100);
        Inventory i2=new Inventory(p2,"Pune",100);
        Inventory i3=new Inventory(p3,"Pune",100);
        Inventory i4=new Inventory(p4,"Pune",100);
        Inventory i5=new Inventory(p5,"Pune",100);

        inventoryRepository.save(i1);
        inventoryRepository.save(i2);
        inventoryRepository.save(i3);
        inventoryRepository.save(i4);
        inventoryRepository.save(i5);

        Orders o1=new Orders(c1,3, UUID.randomUUID());
        Orders o2=new Orders(c1,1,UUID.randomUUID());
        Orders o3=new Orders(c3,2,UUID.randomUUID());
        Orders o4=new Orders(c2,4,UUID.randomUUID());

        Orders so1=orderRepository.save(o1);

        Orders so2=orderRepository.save(o2);
        Orders so3=orderRepository.save(o3);
        Orders so4=orderRepository.save(o4);

        OrderItem oi11=new OrderItem(3,so1,p1,false);
        OrderItem oi12=new OrderItem(2,so1,p3,false);
        OrderItem oi13=new OrderItem(5,so1,p5,false);


        OrderItem soi11=orderItemRepository.save(oi11);
        OrderItem soi12=orderItemRepository.save(oi12);
        OrderItem soi13=orderItemRepository.save(oi13);


        OrderItemLocation oil11=new OrderItemLocation(soi11,"Pune");
        OrderItemLocation oil12=new OrderItemLocation(soi12,"Pune");
        OrderItemLocation oil13=new OrderItemLocation(soi13,"Pune");

        orderItemLocationRepository.save(oil11);
        orderItemLocationRepository.save(oil12);
        orderItemLocationRepository.save(oil13);



        OrderItem oi21=new OrderItem(3,so2,p4,false);
        OrderItem soi21=orderItemRepository.save(oi21);

        OrderItemLocation oil21=new OrderItemLocation(soi21,"Pune");
        orderItemLocationRepository.save(oil21);


        OrderItem oi31=new OrderItem(3,so3,p1,false);
        OrderItem oi32=new OrderItem(1,so3,p3,false);

        OrderItem soi31=orderItemRepository.save(oi31);
        OrderItem soi32=orderItemRepository.save(oi32);


        OrderItemLocation oil31=new OrderItemLocation(soi31,"Pune");
        OrderItemLocation oil32=new OrderItemLocation(soi32,"Pune");

        orderItemLocationRepository.save(oil31);
        orderItemLocationRepository.save(oil32);


        OrderItem oi41=new OrderItem(2,so4,p3,false);
        OrderItem oi42=new OrderItem(6,so4,p1,false);
        OrderItem oi43=new OrderItem(1,so4,p2,false);
        OrderItem oi44=new OrderItem(7,so4,p5,false);

        OrderItem soi41=orderItemRepository.save(oi41);
        OrderItem soi42=orderItemRepository.save(oi42);
        OrderItem soi43=orderItemRepository.save(oi43);
        OrderItem soi44=orderItemRepository.save(oi44);

        OrderItemLocation oil41=new OrderItemLocation(soi41,"Pune");
        OrderItemLocation oil42=new OrderItemLocation(soi42,"Pune");
        OrderItemLocation oil43=new OrderItemLocation(soi43,"Pune");
        OrderItemLocation oil44=new OrderItemLocation(soi44,"Pune");

        orderItemLocationRepository.save(oil41);
        orderItemLocationRepository.save(oil42);
        orderItemLocationRepository.save(oil43);
        orderItemLocationRepository.save(oil44);

    }
}