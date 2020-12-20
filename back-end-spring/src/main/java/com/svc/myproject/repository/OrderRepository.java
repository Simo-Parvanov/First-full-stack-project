package com.svc.myproject.repository;

import com.svc.myproject.domain.entities.Order;
import com.svc.myproject.domain.entities.StatusOrder;
import com.svc.myproject.domain.models.services.OrderServiceModel;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    @Query("select s.orderNumber from Order  as s \n" +
            "where s.statusOrder = :stat1")
    Set<String> findAllOrderByStatus(@Param("stat1") StatusOrder stat1);

    @Query("select s from Order  as s \n" +
            "where s.statusOrder = :stat1")
    List<OrderServiceModel> findAllByStatusOrder(@Param("stat1") StatusOrder stat1);
}
