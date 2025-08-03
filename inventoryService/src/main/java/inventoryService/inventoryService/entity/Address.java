package inventoryService.inventoryService.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Address {
    @JsonProperty("flat_no")
    private String flatNo;
    @JsonProperty("street_or_society_or_colony")
    private String streetOrSocietyOrColony;
    private String city;
    private String state;
    private String zip;

    public Address()
    {

    }

    public Address(String flatNo_,String streetOrSocietyOrColony_,String city_,String state_,String zip_)
    {
        flatNo=flatNo_;
        streetOrSocietyOrColony=streetOrSocietyOrColony_;
        city=city_;
        state=state_;
        zip=zip_;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Address)
        {
            Address a=(Address)o;
            return this.getCity().equals(a.getCity())&& this.getFlatNo().equals(a.getFlatNo())&&this.getState().equals(a.getState())&&this.getZip().equals(a.getZip())&&this.getStreetOrSocietyOrColony().equals(a.getStreetOrSocietyOrColony());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flatNo, streetOrSocietyOrColony, city, state, zip);
    }

    @Override
    public String toString() {
        return "Address{" +
                "flatNo='" + flatNo + '\'' +
                ", streetOrSocietyOrColony='" + streetOrSocietyOrColony + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
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
