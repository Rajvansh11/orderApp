package orderService.orderService.repository;

import orderService.orderService.entity.OrderItem;

import java.util.List;

public interface OrderItemCustomRepository {
    public List<OrderItem>findItemsOfOrder(long orderId);
    public String getOrderItemProduct(long orderItemId);
}