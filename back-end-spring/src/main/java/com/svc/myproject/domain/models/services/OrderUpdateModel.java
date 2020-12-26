package com.svc.myproject.domain.models.services;

import com.svc.myproject.domain.entities.Product;
import com.svc.myproject.domain.entities.StatusOrder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class OrderUpdateModel {
    @NotBlank
    @Size(min = 5, max = 150)
    private String addressBuyer;
    @NotBlank
    private String supplierOffice;
    @NotBlank
    @Size(max = 35)
    @NotBlank
    private String cityName;
    @NotBlank
    private String telephone;
    @Enumerated(EnumType.STRING)
    @NotBlank
    private StatusOrder statusOrder;
    private Set<Product> products;
    private double priceWithoutDiscount;
    private double discountPrice;
    private double totalPrice;
    private double shippingPrice;
    private int orderNumber;
    private String note;

    public OrderUpdateModel() {
    }

    public String getAddressBuyer() {
        return addressBuyer;
    }

    public void setAddressBuyer(String addressBuyer) {
        this.addressBuyer = addressBuyer;
    }

    public String getSupplierOffice() {
        return supplierOffice;
    }

    public void setSupplierOffice(String supplierOffice) {
        this.supplierOffice = supplierOffice;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public double getPriceWithoutDiscount() {
        return priceWithoutDiscount;
    }

    public void setPriceWithoutDiscount(double priceWithoutDiscount) {
        this.priceWithoutDiscount = priceWithoutDiscount;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(double shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
