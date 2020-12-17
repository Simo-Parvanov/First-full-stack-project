package com.svc.myproject.services.impl;

import com.svc.myproject.domain.entities.Order;
import com.svc.myproject.domain.entities.StatusOrder;
import com.svc.myproject.domain.models.services.OrderServiceModel;
import com.svc.myproject.repository.OrderRepository;
import com.svc.myproject.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void createOrder(OrderServiceModel orderServiceModel) {
        Order order = modelMapper.map(orderServiceModel, Order.class);
        order.setDateOfTheOrder(LocalDate.now());
        order.setDateOfDelivery(deliveryDay(order.getDateOfTheOrder()));
        order.setStatusOrder(StatusOrder.ACTIVE);

        System.out.println();
    }

    @Override
    public OrderServiceModel updateOrder(OrderServiceModel orderServiceModel) {
        return null;
    }

    private LocalDate deliveryDay(LocalDate dateOfTheOrder) {
        DayOfWeek dayOfWeek = dateOfTheOrder.plusDays(2).getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SUNDAY){
            return dateOfTheOrder.plusDays(3);
        }else {
            return dateOfTheOrder.plusDays(2);
        }
    }
}
