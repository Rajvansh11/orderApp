package authService.authService.entity;

import jakarta.persistence.*;

@Entity
@Table(name="inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private Products product;
    @Column(name="warehouse_location",nullable = false)
    private String location;
    @Column(name="quantity",nullable = false)
    private int quantity;

    public Inventory() {
    }

    public Inventory(Products product, String location, int quantity) {
        this.product = product;
        this.location = location;
        this.quantity = quantity;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
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
}