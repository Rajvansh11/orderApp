package orderService.orderService.repository;

import orderService.orderService.dto.OrderStatus;
import orderService.orderService.entity.Customers;
import orderService.orderService.entity.OrderItem;
import orderService.orderService.entity.Orders;
import orderService.orderService.exception.CustomException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom
{
    private final EntityManager em;
    private final OrderRepository orderRepository;
    private final OrderItemLocationRepository orderItemLocationRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemCustomRepositoryImpl orderItemCustomRepositoryImpl;
    private final OrderItemLocationCustomRepositoryImpl orderItemLocationCustomRepositoryImpl;
    OrderRepositoryCustomImpl(EntityManager em_, OrderRepository orderRepository_, OrderItemLocationRepository orderItemLocationRepository_, CustomerRepository customerRepository_,OrderItemCustomRepositoryImpl orderItemCustomRepositoryImpl_,OrderItemLocationCustomRepositoryImpl orderItemLocationCustomRepositoryImpl_) {
        em = em_;
        orderRepository = orderRepository_;
        orderItemLocationRepository = orderItemLocationRepository_;
        customerRepository = customerRepository_;
        orderItemCustomRepositoryImpl = orderItemCustomRepositoryImpl_;
        orderItemLocationCustomRepositoryImpl =orderItemLocationCustomRepositoryImpl_;
    }

    @Override
    public List<Orders> getOrdersFromCustomerIdAndStatus(OrderStatus status, long customerId) throws CustomException {
        if (!customerRepository.findById(customerId).isPresent()) {
            throw new CustomException("Customer with the given id does not exist");
        }
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Orders> cq = cb.createQuery(Orders.class);
        Root<Orders> root = cq.from(Orders.class);
        Join<Orders, Customers> join = root.join("customer");

        Predicate statusPredicate = cb.equal(root.get("status"), status);//root.get bcoz we are getting status from Orders
        Predicate customerIdPredicate = cb.equal(join.get("id"), customerId);//join.get bcoz the customer id is in the customers table
        Predicate finalPredicate = cb.and(statusPredicate, customerIdPredicate);

        cq.multiselect(root).where(finalPredicate);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<OrderLocationDto> getOrderItemTracking(long orderId) {
        Orders o = orderRepository.findById(orderId).orElseThrow(() -> new CustomException("Order not present"));
        CriteriaBuilder cb = em.getCriteriaBuilder();
        List<OrderItem>orderItems=orderItemCustomRepositoryImpl.findItemsOfOrder(orderId);
        /*
        make OrderLocationDto
        this has the product name of each item and all the list of locations of each item
         */
    }
}