package orderService.orderService.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="product_id")
    private long id;

    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false,unique = true)
    private String sku;
    @Column(nullable = false)
    private int quantity;

    @Column(name="product_name",nullable = false)
    private String productName;
    @Column(name="cost_price",nullable = false)
    private int costPrice;
    @Column(name="selling_price",nullable = false)
    private int sellingPrice;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItem;

    public Products(String size_,String color_,String sku_,int quantity_,int cp,int sp,String productName_)
    {
        size=size_;
        color=color_;
        sku=sku_;
        quantity=quantity_;
        costPrice=cp;
        sellingPrice=sp;
        productName=productName_;
    }

    public Products() {
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}