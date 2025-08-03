package customerService.customerService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import customerService.customerService.entity.Products;

public class StoreListedProducts
{
    @JsonProperty("product_id")
    private long productId;
    private String size;
    private String color;

    private int quantity;
    private String name;

    public static StoreListedProducts mapProductToStoreListedProduct(Products p)
    {
        return new StoreListedProducts(p.getId(),p.getSize(),p.getColor(),p.getQuantity());
    }

    public StoreListedProducts( ) {
    }

    public StoreListedProducts(long productId, String size, String color, int quantity) {
        this.productId = productId;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}