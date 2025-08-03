package orderService.orderService.dto;

import java.io.Serializable;

public class OrderConfirmationKafkaDto implements Serializable {
    private long customerId;
    private Long orderId;
    private OrderStatus status;//stored if the order was successfully placed or not

    public OrderConfirmationKafkaDto() {
    }

    public OrderConfirmationKafkaDto(long customerId, Long orderId, OrderStatus status) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.status = status;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
