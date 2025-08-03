package orderService.orderService.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class OrderItemsLocationDto
{
    @JsonProperty("arrived_at")
    private LocalDateTime arrivedAt;
    @JsonProperty("location")
    private String location;

    public OrderItemsLocationDto() {
    }

    public OrderItemsLocationDto(LocalDateTime arrivedAt, String location) {
        this.arrivedAt = arrivedAt;
        this.location = location;
    }

    public void setArrivedAt(LocalDateTime arrivedAt) {
        this.arrivedAt = arrivedAt;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getArrivedAt() {
        return arrivedAt;
    }

    public String getLocation() {
        return location;
    }
}