package com.example.ProductService.service;
import com.example.KafkaMessage;
import com.example.ProductDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;
    private static final String TOPIC = "product-topic";

    public KafkaProducerService(KafkaTemplate<String, KafkaMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String action, ProductDTO productDTO) {
        KafkaMessage message = new KafkaMessage(action, productDTO);
        kafkaTemplate.send(TOPIC, message);
    }
}
