package com.svc.myproject.services;

import com.svc.myproject.domain.entities.Order;
import com.svc.myproject.domain.models.services.OrderServiceModel;

import java.util.Optional;
import java.util.Set;

public interface OrderService {
    void createOrder(OrderServiceModel orderServiceModel);

    OrderServiceModel updateOrder(OrderServiceModel orderServiceModel);

    Set<String> findAllOrderNumsByStatus(String status);

    OrderServiceModel findOrderByOrderNumber(String orderNumber);
}
