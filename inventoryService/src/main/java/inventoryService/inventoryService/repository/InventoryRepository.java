package inventoryService.inventoryService.repository;

import inventoryService.inventoryService.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query("select i from Inventory i where i.product.id = :pid and i.location = :l")
    Optional<Inventory> findByProductIdAndLocation(@Param("pid") long id, @Param("l") String location);
}