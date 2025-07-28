package authService.authService.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "customers", uniqueConstraints = {
        @UniqueConstraint(
                name = "fn-ln-address-uk", columnNames = {"first_name", "last_name", "address"})
})

public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "address", columnDefinition = "jsonb",nullable = false)
    private Address address;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;

    @Column(name = "phone_number",nullable = false,unique = true)
    private String phoneNumber;

    @Column(name = "email_id",nullable = false,unique = true)
    private String emailId;


    public Customers() {
    }

    public Customers(String firstName_, String lastName_, Address address_, String phoneNumber_, String emailId_) {
        firstName = firstName_;
        lastName = lastName_;
        address = address_;
        emailId = emailId_;
        phoneNumber = phoneNumber_;
    }

    public Customers(String firstName_, String lastName_, String flatNo_, String streetOrSocietyOrColony_, String city_, String state_, String zip_, String phoneNumber_, String emailId_) {
        Address address = new Address(flatNo_, streetOrSocietyOrColony_, city_, state_, zip_);
        new Customers(firstName_, lastName_, address, phoneNumber_, emailId_);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}