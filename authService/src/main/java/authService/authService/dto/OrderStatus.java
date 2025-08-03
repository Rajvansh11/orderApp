package authService.authService.dto;
public enum OrderStatus {
    PLACED("placed"),FAILED("failed"),PENDING("pending"),FULFILLED("fulfilled");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    @Override
    public String toString() {
        return status;
    }
}