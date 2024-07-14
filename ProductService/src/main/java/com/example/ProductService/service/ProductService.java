package com.example.ProductService.service;

import com.example.ProductService.model.Product;
import com.example.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.example.Product;
import java.util.List;
import java.util.Optional;
import com.example.ProductDTO;

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

        ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getDescription());

        kafkaProducerService.sendMessage("CREATE", productDTO);
        return createdProduct;
    }
    public Product updateProduct(Long id, Product productData) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productData.getName());
        product.setDescription(productData.getDescription());
        Product updatedProduct =  productRepository.save(product);

        ProductDTO productDTO = new ProductDTO(updatedProduct.getId(), updatedProduct.getName(), updatedProduct.getDescription());


        kafkaProducerService.sendMessage("UPDATE", productDTO);
        return updatedProduct;

    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
        ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getDescription());
        kafkaProducerService.sendMessage("DELETE", productDTO);
    }
}



