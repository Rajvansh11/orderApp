package orderService.orderService.repository;

import orderService.orderService.entity.OrderItemLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemLocationRepository extends JpaRepository<OrderItemLocation,Long> {
}
