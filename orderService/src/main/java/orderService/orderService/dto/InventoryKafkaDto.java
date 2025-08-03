package orderService.orderService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InventoryKafkaDto {
    @JsonProperty(value="product_id",required=true)
    private long productId;
    @JsonProperty(value="location",required = true)
    private String location;
    @JsonProperty(value="quantity",required = true)
    private int quantity;

    public InventoryKafkaDto() {
    }

    public InventoryKafkaDto(long productId, String location, int quantity) {
        this.productId = productId;
        this.location = location;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "InventoryKafkaDto{" +
                "productName='" + productId + '\'' +
                ", location='" + location + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}