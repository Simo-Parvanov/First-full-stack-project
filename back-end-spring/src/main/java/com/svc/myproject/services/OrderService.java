package com.svc.myproject.services;

import com.svc.myproject.domain.entities.Order;
import com.svc.myproject.domain.models.services.OrderServiceModel;
import com.svc.myproject.domain.models.services.OrderUpdateModel;

import java.util.List;
import java.util.Set;

public interface OrderService {
    void createOrder(OrderServiceModel orderServiceModel);

    OrderServiceModel updateOrder(OrderServiceModel orderServiceModel);

    Set<String> findAllOrderNumsByStatus(String status);

    OrderServiceModel findOrderByOrderNumber(String orderNumber);

    List<OrderServiceModel> getAllOrders();

    OrderServiceModel update(String id, OrderUpdateModel updateModel);

    Order findOrderById(String id);
}
