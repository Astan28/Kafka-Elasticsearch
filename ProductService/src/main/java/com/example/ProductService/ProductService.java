package com.example.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        if (productRepository.findByName(product.getName()).isPresent()) {
            throw new RuntimeException("Product with name: " + product.getName() + "already exists");
        }

        Product createdProduct = productRepository.save(product);
        kafkaProducerService.sendMessage("CREATE", createdProduct);
        return createdProduct;
    }

    public Product updateProduct(Long id, Product productData) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productData.getName());
        product.setDescription(productData.getDescription());
        Product updatedProduct =  productRepository.save(product);

        kafkaProducerService.sendMessage("UPDATE", updatedProduct);
        return updatedProduct;

    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
        kafkaProducerService.sendMessage("DELETE", product);
    }
}
