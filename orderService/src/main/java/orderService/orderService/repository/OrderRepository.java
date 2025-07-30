package orderService.orderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orderService.orderService.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}