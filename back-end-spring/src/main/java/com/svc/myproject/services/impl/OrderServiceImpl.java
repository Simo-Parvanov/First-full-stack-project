package com.svc.myproject.services.impl;

import com.svc.myproject.domain.entities.Order;
import com.svc.myproject.domain.entities.Product;
import com.svc.myproject.domain.entities.StatusOrder;
import com.svc.myproject.domain.models.services.OrderServiceModel;
import com.svc.myproject.domain.models.services.OrderUpdateModel;
import com.svc.myproject.domain.models.services.ProductServiceModelView;
import com.svc.myproject.repository.OrderRepository;
import com.svc.myproject.services.EmailService;
import com.svc.myproject.services.OrderService;
import com.svc.myproject.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final EmailService emailService;
    private final ProductService productService;


    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, EmailService emailService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.emailService = emailService;

        this.productService = productService;
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
        if (totalPrice < 100) {
            order.setShippingPrice(10);
        } else {
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
        return modelMapper.map(orderRepository.findOrderByOrderNumber(orderNumber).get(), OrderServiceModel.class);
    }

    @Override
    public List<OrderServiceModel> getAllOrders() {
        return orderRepository.findAll().stream().map(order -> {
            return modelMapper.map(order, OrderServiceModel.class);
        }).collect(Collectors.toList());
    }


//    private double discountPrice;
//    private double totalPrice;
//    private double shippingPrice;
//    private int orderNumber;
//    private String note;

    @Override
    public OrderServiceModel update(String id, OrderUpdateModel updateModel) {
        Optional<Order> order = orderRepository.findById(id);
        order.get().setAddressBuyer(updateModel.getAddressBuyer());
        order.get().setSupplierOffice(updateModel.getSupplierOffice());
        order.get().setCityName(updateModel.getCityName());
        order.get().setTelephone(updateModel.getTelephone());
        order.get().setStatusOrder(updateModel.getStatusOrder());
        order.get().setProducts(updateModel.getProducts());
        double totalPrice = totalPrice(updateModel.getProducts());
        double priceWithoutDiscount = sumWithoutDiscount(updateModel.getProducts());
        order.get().setPriceWithoutDiscount(priceWithoutDiscount);
        order.get().setTotalPrice(totalPrice);
        order.get().setDiscountPrice(priceWithoutDiscount - totalPrice);
        if (totalPrice < 100) {
            order.get().setShippingPrice(10);
        } else {
            order.get().setShippingPrice(0);
        }
        order.get().setNote(updateModel.getNote());
        orderRepository.saveAndFlush(order.get());

        return modelMapper.map(order, OrderServiceModel.class);
    }

    @Override
    public Order findOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
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
        while (true) {
            int number = rnd.nextInt(999999);
            num = String.format("%06d", number);
            if (!listOrder.contains(num)) {
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
            ProductServiceModelView productModel = productService.getProductByModel(product.getModel());
            sum += productModel.getPriceOld() * product.getProductCount();
        }
        return sum;
    }

    private String orderNotification(OrderServiceModel orderServiceModel) {
        return "TEST";
    }
}
