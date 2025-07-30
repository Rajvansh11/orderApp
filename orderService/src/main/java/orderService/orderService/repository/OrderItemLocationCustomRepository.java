package orderService.orderService.repository;

import jakarta.persistence.EntityManager;
import orderService.orderService.entity.OrderItemLocation;

import java.util.List;

public interface OrderItemLocationCustomRepository
{
   public List<OrderItemLocation>getAllPastLocationsOfOrderItem(long orderItemId);
}