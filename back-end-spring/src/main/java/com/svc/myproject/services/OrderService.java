package com.svc.myproject.services;

import com.svc.myproject.domain.models.services.OrderServiceModel;

import java.util.Set;

public interface OrderService {
    void createOrder(OrderServiceModel orderServiceModel);

    OrderServiceModel updateOrder(OrderServiceModel orderServiceModel);

    Set<String> findAllOrderNumsByStatus(String status);
}
