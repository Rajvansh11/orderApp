package inventoryService.inventoryService.service;

import inventoryService.inventoryService.dto.InventoryKafkaDto;
import inventoryService.inventoryService.entity.Inventory;
import inventoryService.inventoryService.entity.Products;
import inventoryService.inventoryService.exception.CustomException;
import inventoryService.inventoryService.repository.InventoryRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProcessOrderItemInventory {

    private final InventoryRepository inventoryRepository;

    public ProcessOrderItemInventory(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    private final static Logger log=LoggerFactory.getLogger(ProcessOrderItemInventory.class);


    @KafkaListener(topics = "${kafka.topic.order-inventory-update}", groupId = "inventory-order-consumer-group")
    public void handleOrderInventory(@Payload InventoryKafkaDto inventoryKafkaDto,
            @Header(KafkaHeaders.ACKNOWLEDGMENT) Acknowledgment ack) throws CustomException {
        log.info("Received request to handle inventory of -> {}", inventoryKafkaDto);

        Inventory previousInventory = inventoryRepository
                .findByProductIdAndLocation(inventoryKafkaDto.getProductId(), inventoryKafkaDto.getLocation())
                .orElseThrow(() -> new CustomException("Inventory not found!"));

        previousInventory.setQuantity(previousInventory.getQuantity() - inventoryKafkaDto.getQuantity());
        inventoryRepository.save(previousInventory);

        log.info("Quantity of the item -> {} has been updated in the DB",inventoryKafkaDto);
        ack.acknowledge();
    }
}