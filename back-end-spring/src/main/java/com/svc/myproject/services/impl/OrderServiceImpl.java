package com.svc.myproject.services.impl;

import com.svc.myproject.domain.entities.Order;
import com.svc.myproject.domain.entities.Product;
import com.svc.myproject.domain.entities.StatusOrder;
import com.svc.myproject.domain.models.services.OrderServiceModel;
import com.svc.myproject.repository.OrderRepository;
import com.svc.myproject.services.EmailService;
import com.svc.myproject.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final EmailService emailService;


    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, EmailService emailService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.emailService = emailService;

    }


    @Override
    public void createOrder(OrderServiceModel orderServiceModel) {

        Order order = modelMapper.map(orderServiceModel, Order.class);
        order.setDateOfTheOrder(LocalDate.now());
        order.setDateOfDelivery(deliveryDay(order.getDateOfTheOrder()));
        order.setStatusOrder(StatusOrder.ACTIVE);

        String orderNum = orderNumberGenerator();
        double totalPrice = totalPrice(orderServiceModel.getProducts());
        double priceWithoutDiscount = sumWithoutDiscount(orderServiceModel.getProducts());
        order.setOrderNumber(orderNum);
        order.setTotalPrice(totalPrice);
        order.setPriceWithoutDiscount(priceWithoutDiscount);
        order.setDiscountPrice(priceWithoutDiscount - totalPrice);
        if (totalPrice > 100){
            order.setShippingPrice(10);
        }else {
            order.setShippingPrice(0);
        }
        orderRepository.saveAndFlush(order);
        emailService.sendEmail(orderServiceModel.getEmail(),
                orderNum,
                orderNotification(orderServiceModel));
    }

    @Override
    public OrderServiceModel updateOrder(OrderServiceModel orderServiceModel) {
        return null;
    }

    @Override
    public Set<String> findAllOrderNumsByStatus(String status) {
        return orderRepository.findAllOrderByStatus(StatusOrder.valueOf(status.toUpperCase()));
    }

    @Override
    public OrderServiceModel findOrderByOrderNumber(String orderNumber) {
        return modelMapper.map(orderRepository.findOrderByOrderNumber(orderNumber).get(),OrderServiceModel.class);
    }

    private LocalDate deliveryDay(LocalDate dateOfTheOrder) {
        DayOfWeek dayOfWeek = dateOfTheOrder.plusDays(2).getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SUNDAY) {
            return dateOfTheOrder.plusDays(3);
        } else {
            return dateOfTheOrder.plusDays(2);
        }
    }

    private String orderNumberGenerator() {
        Random rnd = new Random();
        String num;
        Set<String> listOrder = findAllOrderNumsByStatus("active");
        while (true){
            int number = rnd.nextInt(999999);
            num = String.format("%06d", number);
            if (!listOrder.contains(num)){
                return num;
            }
        }
    }
    private double totalPrice(Set<Product> products) {
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice() * product.getProductCount();
        }
        return sum;
    }
    private double sumWithoutDiscount(Set<Product> products) {
        double sum = 0;
        for (Product product : products) {
            sum += product.getPriceOld() * product.getProductCount();
        }
        return sum;
    }

    private String orderNotification(OrderServiceModel orderServiceModel) {
        return "TEST";
    }
}
