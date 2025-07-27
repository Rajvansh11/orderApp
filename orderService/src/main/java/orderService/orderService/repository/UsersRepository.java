package orderService.orderService.repository;

import orderService.orderService.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long>
{

}
