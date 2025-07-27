package customerService.customerService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CustomerOrderProcessingDto {
    @JsonProperty("status")
    private OrderStatus status;
    @JsonProperty("customer-order-uuid")
    private UUID customerOrderUUID;

    public CustomerOrderProcessingDto() {
    }

    public CustomerOrderProcessingDto(OrderStatus status_, UUID customerOrderUUID) {
        this.status = status_;
        this.customerOrderUUID = customerOrderUUID;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public UUID getCustomerOrderUUID() {
        return customerOrderUUID;
    }

    public void setCustomerOrderUUID(UUID customerOrderUUID) {
        this.customerOrderUUID = customerOrderUUID;
    }
}
