package com.example.SearchService.service;

import com.example.SearchService.KafkaMessage;
import com.example.SearchService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private ProductRepository productRepository;

    @KafkaListener(topics = "product-topic", groupId = "my-group")
    public void listen(KafkaMessage kafkaMessage) {
        System.out.println("consumed action: "+ kafkaMessage.getAction() + "Product - " + kafkaMessage.getProduct());
        if ("CREATE".equals(kafkaMessage.getAction()) || "UPDATE".equals(kafkaMessage.getAction())) {
            productRepository.save(kafkaMessage.getProduct());
        } else if ("DELETE".equals(kafkaMessage.getAction())) {
            productRepository.deleteById(kafkaMessage.getProduct().getId());
        }

    }
}
