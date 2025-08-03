package inventoryService.inventoryService.config;

import inventoryService.inventoryService.dto.InventoryKafkaDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaInventoryConsumerConfig {
    @Bean
    public ConsumerFactory<Long, InventoryKafkaDto>inventoryConsumerConfig()
    {
        Map<String,Object>map=new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        map.put(ConsumerConfig.GROUP_ID_CONFIG,"inventory-order-consumer-group");
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        map.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);

        map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        map.put(JsonDeserializer.REMOVE_TYPE_INFO_HEADERS,"false");
        map.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        map.put(JsonDeserializer.VALUE_DEFAULT_TYPE, InventoryKafkaDto.class.getName());
        map.put(JsonDeserializer.TYPE_MAPPINGS, "order:inventoryService.inventoryService.dto.OrderKafkaDto");

        return new DefaultKafkaConsumerFactory<>(map);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, InventoryKafkaDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, InventoryKafkaDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(inventoryConsumerConfig());
        factory.setConcurrency(1);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }
}
