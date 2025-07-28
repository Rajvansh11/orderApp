package customerService.customerService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDetailsDto {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("email_id")
    private String emailId;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("flat_no")
    private String flatNo;

    @JsonProperty("street_or_society_or_colony")
    private String streetOrSocietyOrColony;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("zip")
    private String zip;

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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getStreetOrSocietyOrColony() {
        return streetOrSocietyOrColony;
    }

    public void setStreetOrSocietyOrColony(String streetOrSocietyOrColony) {
        this.streetOrSocietyOrColony = streetOrSocietyOrColony;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
