package customerService.customerService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleOrderItemDto {
    private int quantity;
    @JsonProperty("product_id")
    private long productId;

    public SingleOrderItemDto() {
    }

    public SingleOrderItemDto(int quantity, long productId) {
        this.quantity = quantity;
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
