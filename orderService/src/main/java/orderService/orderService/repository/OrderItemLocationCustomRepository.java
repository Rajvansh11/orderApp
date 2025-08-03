package orderService.orderService.repository;

import jakarta.persistence.EntityManager;
import orderService.orderService.dto.OrderItemDetailsDto;
import orderService.orderService.entity.OrderItemLocation;

import java.util.List;

public interface OrderItemLocationCustomRepository
{
   public OrderItemDetailsDto getAllPastLocationsOfOrderItem(long orderItemId);
}