package customerService.customerService.service;

import customerService.customerService.dto.OrderConfirmationKafkaDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 can be removed
 *
 */
//@Service
//public class CustomerOrderConfirmationService {
//    @KafkaListener(topics = "${kafka.topic.customer-order-confirmation}", groupId = "customer-service")
//    public void handleConfirmation(@Payload OrderConfirmationKafkaDto confirmation) {
//
//        // Optionally update UI/cache/status or trigger a webhook
//    }
//}
