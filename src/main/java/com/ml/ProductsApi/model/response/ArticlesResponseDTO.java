package com.ml.ProductsApi.model.response;

public class ArticlesResponseDTO {
    String name, category, brand, prestige;
    int price, quantity;
    boolean freeShipping;

    public ArticlesResponseDTO(String name, String category, String brand, String prestige, int price, int quantity, boolean freeShipping) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.prestige = prestige;
        this.price = price;
        this.quantity = quantity;
        this.freeShipping = freeShipping;
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

    public String getPrestige() {
        return prestige;
    }

    public void setPrestige(String prestige) {
        this.prestige = prestige;
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

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }
}
