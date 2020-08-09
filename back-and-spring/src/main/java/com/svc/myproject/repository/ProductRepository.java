package com.svc.myproject.repository;

import com.svc.myproject.domain.entities.CategoryEnum;
import com.svc.myproject.domain.entities.CategoryEnumTwo;
import com.svc.myproject.domain.entities.ERole;
import com.svc.myproject.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("select p from Product  as p \n" +
            "    join Category c on p.category.id = c.id \n" +
            "join SubcategoryTwo sct on c.subcategoryTwo.id = sct.id \n" +
            "where c.firstCategory = :cat1 and sct.secondCategory = :cat2")
    List<Product> findProductByTwoParams(@Param("cat1") CategoryEnum cat1,
                                         @Param("cat2") CategoryEnumTwo cat2);

    @Query("select p from Product  as p \n" +
            "    join Category c on p.category.id = c.id \n" +
            "where c.firstCategory = :cat1")
    List<Product> findProductByOneParams(@Param("cat1")CategoryEnum cat1);
 }
