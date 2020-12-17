package com.svc.myproject.services;

import com.svc.myproject.domain.models.services.OrderServiceModel;

public interface OrderService {
    void createOrder(OrderServiceModel orderServiceModel);

    OrderServiceModel updateOrder(OrderServiceModel orderServiceModel);
}
