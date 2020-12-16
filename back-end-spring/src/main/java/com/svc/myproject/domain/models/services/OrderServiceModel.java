package com.svc.myproject.domain.models.services;

import com.svc.myproject.domain.entities.Product;
import com.svc.myproject.domain.entities.StatusOrder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

public class OrderServiceModel {
    @NotBlank
    @Column(name = "buyer_first_name", nullable = false)
    private String buyerFirstName;
    @NotBlank
    @Column(name = "buyer_last_name", nullable = false)
    private String buyerLastName;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Column(name = "address_buyer", nullable = false)
    @Size(min = 5, max = 150)
    private String addressBuyer;
    @NotBlank
    @Column(name = "supplier_office", nullable = false)
    private String supplierOffice;
    @NotBlank
    @Column(name = "city_name", nullable = false)
    @Size(max = 35)
    private String cityName;
    @NotBlank
    @Column(name = "telephone", nullable = false)
    private String telephone;
    @Column(name = "date_of_the_order")
    private LocalDate dateOfTheOrder;
    @Column(name = "date_of_delivery")
    private LocalDate dateOfDelivery;
    @Column(name = "status_order")
    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Product> productSet;
    @Column(name = "price_without_discount")
    private double priceWithoutDiscount;
    @Column(name = "discount_price")
    private double discountPrice;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "shipping_price")
    private double shippingPrice;

    public OrderServiceModel() {
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDate getDateOfTheOrder() {
        return dateOfTheOrder;
    }

    public void setDateOfTheOrder(LocalDate dateOfTheOrder) {
        this.dateOfTheOrder = dateOfTheOrder;
    }

    public LocalDate getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(LocalDate dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
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
}
