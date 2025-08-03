package customerService.customerService.repository;

import customerService.customerService.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long>
{
}