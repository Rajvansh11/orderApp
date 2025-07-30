package orderService.orderService.repository;

import orderService.orderService.entity.OrderItem;
import orderService.orderService.entity.Orders;
import orderService.orderService.entity.Products;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

public class OrderItemCustomRepositoryImpl implements OrderItemCustomRepository{

    private final EntityManager em;
    public OrderItemCustomRepositoryImpl(EntityManager em_)
    {
        em=em_;
    }
    @Override
    public List<OrderItem> findItemsOfOrder(long orderId) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<OrderItem>cq=cb.createQuery(OrderItem.class);
        Root<OrderItem>root=cq.from(OrderItem.class);

        Join<OrderItem, Orders>join=root.join("order");
        Predicate orderIdPredicate=cb.equal(join.get("id"),orderId);

        cq.multiselect(root).where(orderIdPredicate);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public String getOrderItemProduct(long orderItemId) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<String>cq=cb.createQuery(String.class);
        Root<OrderItem>root=cq.from(OrderItem.class);

        Join<OrderItem, Products>join=root.join("product");

        cq.select(join.get("productName")).where(cb.equal(root.get("id"), orderItemId));
        return em.createQuery(cq).getSingleResult();
    }
}