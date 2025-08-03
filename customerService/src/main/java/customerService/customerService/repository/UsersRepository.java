package customerService.customerService.repository;

import customerService.customerService.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long>
{

}