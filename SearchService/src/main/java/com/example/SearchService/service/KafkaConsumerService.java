package com.example.SearchService.service;

import com.example.KafkaMessage;
import com.example.SearchService.document.Product;
import com.example.SearchService.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final ProductRepository productRepository;

    public KafkaConsumerService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @KafkaListener(topics = "product-topic", groupId = "my-group")
    public void listen(KafkaMessage kafkaMessage) {
         System.out.println("Consumed action: " + kafkaMessage.getAction() + " Product - " + kafkaMessage.getProductDTO().getName());
        Product product = new Product();
        product.setId(kafkaMessage.getProductDTO().getId());
        product.setName(kafkaMessage.getProductDTO().getName());
        product.setDescription(kafkaMessage.getProductDTO().getDescription());
        System.out.println("Product: " + product);
        if ("CREATE".equals(kafkaMessage.getAction()) || "UPDATE".equals(kafkaMessage.getAction())) {
            productRepository.save(product);
        } else if ("DELETE".equals(kafkaMessage.getAction())) {
            productRepository.deleteById(product.getId());
        }
    }
}
