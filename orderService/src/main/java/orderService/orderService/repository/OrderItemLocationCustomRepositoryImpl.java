package orderService.orderService.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import orderService.orderService.dto.OrderItemDetailsDto;
import orderService.orderService.dto.OrderItemsLocationDto;
import orderService.orderService.entity.OrderItem;
import orderService.orderService.entity.OrderItemLocation;
import orderService.orderService.exception.CustomException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderItemLocationCustomRepositoryImpl implements OrderItemLocationCustomRepository
{
    private final EntityManager em;
    private final OrderItemRepository oil;
    OrderItemLocationCustomRepositoryImpl(EntityManager em_,OrderItemRepository oil_)
    {
        em=em_;
        oil=oil_;
    }
    @Override
    public OrderItemDetailsDto getAllPastLocationsOfOrderItem(long orderItemId) throws CustomException{
       OrderItem oi=oil.findById(orderItemId).orElseThrow(()->new CustomException("Order Item not found"));
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<OrderItemLocation> cq=cb.createQuery(OrderItemLocation.class);
        Root<OrderItemLocation> root=cq.from(OrderItemLocation.class);

        Join<OrderItemLocation, OrderItem> join=root.join("orderItem");
        Predicate orderItemIdPRedicate=cb.equal(join.get("id"),orderItemId);

        cq.multiselect(root).where(orderItemIdPRedicate).orderBy(cb.asc(root.get("arrivedAt")));
        List<OrderItemLocation>orderItemList =em.createQuery(cq).getResultList();
        List<OrderItemsLocationDto> orderItemsLocationDtos = orderItemList.stream()
                .map(oil -> new OrderItemsLocationDto(oil.getArrivedAt(), oil.getLocation()))
                .collect(Collectors.toList());
        return new OrderItemDetailsDto(oi.getProduct().getProductName(),oi.getQuantity(),orderItemsLocationDtos);
    }
}