package customerService.customerService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class OrderItemKafkaDto implements Serializable {

    @JsonProperty(value="quantity",required = true)
    private int quantity;

    //assume that the main warehouse is in Pune
    @JsonProperty(value="current_location",required = true,defaultValue ="Pune,MH")
    private String location;

    @JsonProperty(value="product_id",required = true)
    private long productId;

    public OrderItemKafkaDto() {
    }

    public OrderItemKafkaDto(int quantity, String location, long productId) {
        this.quantity = quantity;
        this.location = location;
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrderItemKafkaDto{" +
                "quantity=" + quantity +
                ", location='" + location + '\'' +
                ", productId=" + productId +
                '}';
    }
}