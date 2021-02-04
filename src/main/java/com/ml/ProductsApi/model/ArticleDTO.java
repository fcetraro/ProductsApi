package com.ml.ProductsApi.model;

import com.ml.ProductsApi.model.read.ArticleFromJsonDTO;

public class ArticleDTO {
    String name, category, brand;
    int price, quantity, prestige, id;
    boolean freeShipping;

    public ArticleDTO(int id, ArticleFromJsonDTO articles) {
        this.name = articles.getName();
        this.category = articles.getCategory();
        this.brand = articles.getBrand();
        this.price = articles.getPrice();
        this.quantity = articles.getQuantity();
        this.prestige = articles.getPrestige();
        this.id = id;
        this.freeShipping = articles.isFreeShipping();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }
}
