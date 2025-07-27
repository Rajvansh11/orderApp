package customerService.customerService.repository;

import customerService.customerService.dto.OrderStatus;
import customerService.customerService.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders,Long> {

    @Query("SELECT o FROM Orders o WHERE o.uuid = :uuid_")
    Optional<Orders> findByUUID(@Param("uuid_") UUID uuid);

    @Query("SELECT o FROM Orders o WHERE o.uuid = :uuid_ AND o.status = :status_")
    Optional<Orders> findByUUIDAndStatus(@Param("uuid_") UUID uuid, @Param("status_") OrderStatus status);

}