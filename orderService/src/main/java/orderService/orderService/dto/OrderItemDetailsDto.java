package orderService.orderService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderItemDetailsDto {
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("order_item_id")
    private long orderItemId;
    @JsonProperty("location")
    private List<OrderItemsLocationDto> locationDtoList;

    public OrderItemDetailsDto() {
    }

    public OrderItemDetailsDto(String productName, long orderItemId, List<OrderItemsLocationDto> locationDtoList) {
        this.productName = productName;
        this.orderItemId = orderItemId;
        this.locationDtoList = locationDtoList;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public List<OrderItemsLocationDto> getLocationDtoList() {
        return locationDtoList;
    }

    public void setLocationDtoList(List<OrderItemsLocationDto> locationDtoList) {
        this.locationDtoList = locationDtoList;
    }
}