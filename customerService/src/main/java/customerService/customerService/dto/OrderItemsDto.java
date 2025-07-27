package customerService.customerService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderItemsDto
{
    @JsonProperty("order_items")
    private List<SingleOrderItemDto>orderItems;
    @JsonProperty("customer_id")
    private long customerId;

    public OrderItemsDto() {
    }

    public OrderItemsDto(List<SingleOrderItemDto> orderItems, long customerId) {
        this.orderItems = orderItems;
        this.customerId = customerId;
    }

    public List<SingleOrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<SingleOrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

}
