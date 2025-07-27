package customerService.customerService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import customerService.customerService.entity.Address;

public class AddCustomerDto {
    @JsonProperty("address")
    private Address address;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("email-id")
    private String emailId;

    public AddCustomerDto() {
    }

    public AddCustomerDto(Address address, String firstName, String lastName, String phoneNumber, String emailId) {
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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