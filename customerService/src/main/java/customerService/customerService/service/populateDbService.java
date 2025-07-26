package customerService.customerService.service;

import customerService.customerService.entity.*;
import customerService.customerService.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class populateDbService implements CommandLineRunner {

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
    OrderItemLocationRepository orderItemLocationRepository;

    @Override
    public void run(String... args) throws Exception {
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

        Orders o1=new Orders(c1,3);
        Orders o2=new Orders(c1,1);
        Orders o3=new Orders(c3,2);
        Orders o4=new Orders(c2,4);

        orderRepository.save(o1);

        orderRepository.save(o2);
        orderRepository.save(o3);
        orderRepository.save(o4);

        OrderItem oi11=new OrderItem(3,o1,p1,false);
        OrderItem oi12=new OrderItem(2,o1,p3,false);
        OrderItem oi13=new OrderItem(5,o1,p5,false);

        OrderItemLocation oil11=new OrderItemLocation(oi11,"Jamshedpur");
        OrderItemLocation oil12=new OrderItemLocation(oi12,"Pune");
        OrderItemLocation oil13=new OrderItemLocation(oi13,"Ahmedabad");

        OrderItem oi21=new OrderItem(3,o2,p4,false);

        OrderItemLocation oil21=new OrderItemLocation(oi21,"Kolkata");

        OrderItem oi31=new OrderItem(3,o3,p1,false);
        OrderItem oi32=new OrderItem(1,o3,p3,false);

        OrderItemLocation oil31=new OrderItemLocation(oi31,"Mumbai");
        OrderItemLocation oil32=new OrderItemLocation(oi32,"Surat");

        OrderItem oi41=new OrderItem(2,o4,p3,false);
        OrderItem oi42=new OrderItem(6,o4,p1,false);
        OrderItem oi43=new OrderItem(1,o4,p2,false);
        OrderItem oi44=new OrderItem(7,o4,p5,false);

        OrderItemLocation oil41=new OrderItemLocation(oi41,"Banaras");
        OrderItemLocation oil42=new OrderItemLocation(oi42,"Raipur");
        OrderItemLocation oil43=new OrderItemLocation(oi43,"Rourkela");
        OrderItemLocation oil44=new OrderItemLocation(oi44,"Chennai");

        orderItemRepository.save(oi11);
        orderItemRepository.save(oi12);
        orderItemRepository.save(oi13);

        orderItemRepository.save(oi21);

        orderItemRepository.save(oi31);
        orderItemRepository.save(oi32);

        orderItemRepository.save(oi41);
        orderItemRepository.save(oi42);
        orderItemRepository.save(oi43);
        orderItemRepository.save(oi44);

        orderItemLocationRepository.save(oil11);
        orderItemLocationRepository.save(oil12);
        orderItemLocationRepository.save(oil13);

        orderItemLocationRepository.save(oil21);

        orderItemLocationRepository.save(oil31);
        orderItemLocationRepository.save(oil32);

        orderItemLocationRepository.save(oil41);
        orderItemLocationRepository.save(oil42);
        orderItemLocationRepository.save(oil43);
        orderItemLocationRepository.save(oil44);



    }
}