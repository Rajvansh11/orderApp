package orderService.orderService.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import orderService.orderService.entity.OrderItem;
import orderService.orderService.entity.OrderItemLocation;

import java.util.List;

public class OrderItemLocationCustomRepositoryImpl implements OrderItemLocationCustomRepository
{
    private final EntityManager em;
    OrderItemLocationCustomRepositoryImpl(EntityManager em_)
    {
        em=em_;
    }
    @Override
    public List<OrderItemLocation> getAllPastLocationsOfOrderItem(long orderItemId) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<OrderItemLocation> cq=cb.createQuery(OrderItemLocation.class);
        Root<OrderItemLocation> root=cq.from(OrderItemLocation.class);

        Join<OrderItemLocation, OrderItem> join=root.join("orderItem");
        Predicate orderItemIdPRedicate=cb.equal(join.get("id"),orderItemId);

        cq.multiselect(root).where(orderItemIdPRedicate).orderBy(cb.asc(root.get("arrivedAt")));
        return em.createQuery(cq).getResultList();
    }
}
