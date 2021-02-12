package com.ml.ProductsApi.model.request;

public class QuantityArticleDTO {
    int id;
    int quantityBought;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantityBought() {
        return quantityBought;
    }

    public void setQuantityBought(int quantityBought) {
        this.quantityBought = quantityBought;
    }
}
