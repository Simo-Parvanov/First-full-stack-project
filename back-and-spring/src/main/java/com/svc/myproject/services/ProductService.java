package com.svc.myproject.services;

import com.svc.myproject.domain.entities.Product;
import com.svc.myproject.domain.models.services.ProductServiceModel;
import com.svc.myproject.domain.models.services.ProductServiceModelView;
import com.svc.myproject.domain.models.services.ProductServiceUpdateModel;

import java.util.List;

public interface ProductService {
    List<ProductServiceModelView> allProduct();

    void createProduct(ProductServiceModel productServiceModel);

    Product findProductById(String id);

    ProductServiceModelView update(ProductServiceUpdateModel updateProduct, String id);
}
