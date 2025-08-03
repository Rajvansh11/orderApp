package authService.authService.entity;

import authService.authService.dto.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private Long id;

    @CreationTimestamp
    @Column(name="created_at",updatable = false)
    private LocalDateTime createdAt;

    @Column(name="total_line_items",nullable = false)
    private int noOfItems;

    @Column(name="unique-id")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customers customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;


    @Enumerated(EnumType.STRING)
    @Column(name="order_status",nullable = false)
    private OrderStatus status;

    public Orders() {
    }

    public Orders(Customers customer,int noOfItems_,UUID uuid_) {
        this.customer = customer;
        this.noOfItems=noOfItems_;
        this.uuid=uuid_;
        this.status=OrderStatus.PENDING;
    }
    public Orders(Customers customer,int noOfItems_,UUID uuid_,OrderStatus status_) {
        this.customer = customer;
        this.noOfItems=noOfItems_;
        this.uuid=uuid_;
        this.status=status_;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "localDateTime=" + createdAt +
                ", customer=" + customer +
                '}';
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
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
}