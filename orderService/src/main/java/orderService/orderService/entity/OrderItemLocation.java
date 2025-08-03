package orderService.orderService.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_item_location")
public class OrderItemLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @CreationTimestamp
    @Column(name = "arrived_at", updatable = false)
    private LocalDateTime arrivedAt;
    @Column(nullable = false)
    public String location;

    public OrderItemLocation() {

    }

    public OrderItemLocation(OrderItem orderItem, String location) {
        this.orderItem = orderItem;
        this.location = location;
    }

    @Override
    public String toString() {
        return "OrderItemLocation{" +
                "orderItem=" + orderItem +
                ", arrivedAt=" + arrivedAt +
                ", location='" + location + '\'' +
                '}';
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public LocalDateTime getArrivedAt() {
        return arrivedAt;
    }

    public void setArrivedAt(LocalDateTime arrivedAt) {
        this.arrivedAt = arrivedAt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
