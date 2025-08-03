package orderService.orderService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class OrderKafkaDto implements Serializable {

    @JsonProperty(value="customer_id",required=true)
    private Long customerId;

    @JsonProperty(value="order_items",required = true)
    private List<OrderItemKafkaDto>orderItemsList;

    @JsonProperty(value="uuid")
    private UUID uuid;

    @JsonProperty(value="order_status")
    private OrderStatus status;

    public OrderKafkaDto() {
    }

    public OrderKafkaDto( Long customerId,List<OrderItemKafkaDto>orderItemsList_,UUID uuid_, OrderStatus status_) {
        this.customerId = customerId;
        this.orderItemsList=orderItemsList_;
        this.uuid=uuid_;
        this.status=status_;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemKafkaDto> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItemKafkaDto> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderKafkaDto{" +
                "customerId=" + customerId +
                ", orderItemsList=" + orderItemsList +
                ", uuid=" + uuid +
                ", status=" + status +
                '}';
    }
}