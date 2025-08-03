package customerService.customerService.repository;

import customerService.customerService.entity.Address;
import customerService.customerService.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customers,Long> {

    @Query("select c from Customers c where c.emailId = :emailId")
    public Optional<Customers> findByEmailId(@Param("emailId") String emailId);

    @Query("select c from Customers c where c.phoneNumber= :phoneNo")
    public Optional<Customers> findByPhoneNumber(@Param("phoneNo")String pno);

//    @Query("select c from Customers c where c.firstName=:fn and c.lastName=:ln and c.address=:address")
//    public Optional<Customers> findByFirstNameLastNameAndAddress(@Param("fn")String fn,@Param("ln")String ln,@Param("address") Address address);
}