package com.svc.myproject.domain.models.services;

import com.svc.myproject.domain.entities.Category;
import com.svc.myproject.domain.entities.Image;

import java.util.List;
import java.util.Set;

public class ProductServiceModelView {
    private String name;
    private String model;
    private String description;
    private Double price;
    private Double priceOld;
    private int discount;
    private boolean promotion;
    private boolean status;
    private Category category;
    private List<Image> image;

    public ProductServiceModelView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Double priceOld) {
        this.priceOld = priceOld;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
