package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KafkaMessage {
    private String action;
    private ProductDTO productDTO;

    public KafkaMessage() {}

    public KafkaMessage(String action, ProductDTO productDTO) {
        this.action = action;
        this.productDTO = productDTO;
    }

    @JsonProperty
    public String getAction() {
        return action;
    }

    @JsonProperty
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty
    public ProductDTO getProductDTO() {
        return productDTO;
    }

    @JsonProperty
    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }
}
