package com.svc.myproject.services.impl;

import com.svc.myproject.domain.entities.Order;
import com.svc.myproject.domain.entities.StatusOrder;
import com.svc.myproject.domain.models.services.EmailServiceModel;
import com.svc.myproject.domain.models.services.OrderServiceModel;
import com.svc.myproject.repository.OrderRepository;
import com.svc.myproject.services.EmailService;
import com.svc.myproject.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        Order order = modelMapper.map(orderServiceModel, Order.class);
        order.setDateOfTheOrder(LocalDate.now());
        order.setDateOfDelivery(deliveryDay(order.getDateOfTheOrder()));
        order.setStatusOrder(StatusOrder.ACTIVE);
        System.out.println();
        String d = "Hello [name],\n" +
                "\n" +
                "Great news! Your order is on its way.\n" +
                "\n" +
                "This email is to notify you that your order [order number] has successfully shipped. You can click on the button below to track your shipment.\n" +
                "\n" +
                "[CTA]\n" +
                "\n" +
                "Here are the details of your order:\n" +
                "\n" +
                "[order details]\n" +
                "\n" +
                "Best regards,\n" +
                "Team [company name]";
        EmailServiceModel emailServiceModel = new EmailServiceModel();
        emailServiceModel.setTo(orderServiceModel.getEmail());
        emailServiceModel.setSubject("Order №2565488");
        emailServiceModel.setText(d);

        emailService.sendEmail(emailServiceModel);

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
