package orderService.orderService.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="orderItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_item_id")
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Orders order;

    @OneToMany(mappedBy = "orderItem",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItemLocation> orderItemLocations;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Products product;

    @Column(nullable = false)
    private boolean isFulfilled;

    public OrderItem(int quantity, Orders order, Products product,boolean isFulfilled_) {
        this.quantity = quantity;
        this.order = order;
        this.product = product;
        this.isFulfilled=isFulfilled_;
    }

    public OrderItem() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public List<OrderItemLocation> getOrderItemLocations() {
        return orderItemLocations;
    }

    public void setOrderItemLocations(List<OrderItemLocation> orderItemLocations) {
        this.orderItemLocations = orderItemLocations;
    }

    public boolean getFulfilled() {
        return isFulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        isFulfilled = fulfilled;
    }
}