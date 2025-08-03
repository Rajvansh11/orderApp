package orderService.orderService.configuration;

import orderService.orderService.dto.InventoryKafkaDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaInventoryProducerConfig {
    @Bean
    public ProducerFactory<Long, InventoryKafkaDto> inventoryUpdateProducer() {
        Map<String,Object>map=new HashMap<>();
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        map.put(JsonSerializer.ADD_TYPE_INFO_HEADERS,true);

        return new DefaultKafkaProducerFactory<>(map);
    }

    @Bean
    public KafkaTemplate<Long,InventoryKafkaDto>kafkaTemplate()
    {
        return new KafkaTemplate<>(inventoryUpdateProducer());
    }
}