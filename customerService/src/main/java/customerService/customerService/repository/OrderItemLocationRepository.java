package customerService.customerService.repository;

import customerService.customerService.entity.OrderItemLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemLocationRepository extends JpaRepository<OrderItemLocation,Long> {
}
