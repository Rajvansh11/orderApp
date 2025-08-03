package customerService.customerService.repository;

import customerService.customerService.entity.Customers;
import customerService.customerService.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
