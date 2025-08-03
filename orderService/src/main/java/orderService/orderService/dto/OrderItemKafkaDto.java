package orderService.orderService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderItemKafkaDto {
    @JsonProperty("product_id")
    private Long productId;
    private int quantity;

    public OrderItemKafkaDto( ) {
    }

    public OrderItemKafkaDto(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemKafkaDto{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
