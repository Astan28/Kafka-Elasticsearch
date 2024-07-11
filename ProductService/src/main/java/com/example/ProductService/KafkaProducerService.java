package com.example.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    private static final String TOPIC = "product-topic";

    public void sendMessage(String action, Product product) {
        KafkaMessage message = new KafkaMessage(action, product);
        kafkaTemplate.send(TOPIC, message);
    }
}
