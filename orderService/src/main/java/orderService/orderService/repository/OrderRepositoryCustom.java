package orderService.orderService.repository;
import orderService.orderService.dto.OrderItemDetailsDto;
import orderService.orderService.dto.OrderStatus;
import orderService.orderService.entity.Orders;

import java.util.List;

public interface OrderRepositoryCustom
{
    List<Orders> getOrdersFromCustomerIdAndStatus(OrderStatus status, long customerId);
    List<OrderItemDetailsDto>getOrderItemTracking(long orderId);
}