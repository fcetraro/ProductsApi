package com.ml.ProductsApi.model.request;

public class FilterDTO {
    String brand;
    String category;
    String prestige;
    String price;
    String quantity;
    String name;
    String sendFree;

    public FilterDTO(String brand, String category, String prestige, String price, String quantity, String name, String sendFree) {
        this.brand = brand;
        this.category = category;
        this.prestige = prestige;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.sendFree = sendFree;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrestige() {
        return prestige;
    }

    public void setPrestige(String prestige) {
        this.prestige = prestige;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSendFree() {
        return sendFree;
    }

    public void setSendFree(String sendFree) {
        this.sendFree = sendFree;
    }
}
