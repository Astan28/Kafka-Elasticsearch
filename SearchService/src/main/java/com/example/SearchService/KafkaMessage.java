package com.example.SearchService;

import com.example.SearchService.document.Product;

public class KafkaMessage {
    private String action;
    private Product product;

    public KafkaMessage() {
    }

    public KafkaMessage(String action, Product product) {
        this.action = action;
        this.product = product;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

