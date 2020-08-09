package com.svc.myproject.services.impl;

import com.svc.myproject.domain.entities.*;
import com.svc.myproject.domain.models.services.CategoryServiceModel;
import com.svc.myproject.domain.models.services.ProductServiceModel;
import com.svc.myproject.domain.models.services.ProductServiceModelView;
import com.svc.myproject.domain.models.services.ProductServiceUpdateModel;
import com.svc.myproject.repository.ProductRepository;
import com.svc.myproject.services.ImageService;
import com.svc.myproject.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final ImageService imageService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper, ImageService imageService) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.imageService = imageService;
    }

    @Override
    public List<ProductServiceModelView> allProduct() {
        return productRepository.findAll().stream().map(product -> {
            ProductServiceModelView productView  = mapper.map(product, ProductServiceModelView.class);
            List<Image> image = imageService.findAllByModel(productView.getModel());
            productView.setImage(image);
            return productView;
        }).collect(Collectors.toList());
    }

    @Override
    public void createProduct(ProductServiceModel productServiceModel) {
        Product product = mapper.map(productServiceModel, Product.class);
        productRepository.saveAndFlush(product);
    }

    @Override
    public Product findProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.isEmpty()? null : product.get();
    }

    @Override
    public ProductServiceModelView update(ProductServiceUpdateModel updateProduct, String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()){
            return null;
        }
        product.get().setDescription(updateProduct.getDescription());
        if (product.get().getPrice() > updateProduct.getPrice() && updateProduct.isPromotion()){
            product.get().setPriceOld(product.get().getPrice());
            product.get().setDiscount(getDiscount(product.get().getPrice(), updateProduct.getPrice()));
        }else{
            product.get().setPriceOld(null);
            product.get().setDiscount(0);
        }
        product.get().setPrice(updateProduct.getPrice());
        product.get().setPromotion(updateProduct.isPromotion());
        product.get().setStatus(updateProduct.isStatus());
        productRepository.saveAndFlush(product.get());

        return mapAndAddingImage(product.get());
    }

    @Override
    public ProductServiceModelView productById(String id) {
        Optional<Product> product = productRepository.findById(id);
        return mapAndAddingImage(product.get());
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductServiceModelView> findByTwoCategories(CategoryServiceModel model) {
        List<Product> product;
        if (model.getCategorySecond() == null){
            product = productRepository
                    .findProductByOneParams
                            (CategoryEnum.valueOf(model.getCategoryFirst()));
        }else {
            product = productRepository
                    .findProductByTwoParams
                            (CategoryEnum.valueOf(model.getCategoryFirst()),
                                    CategoryEnumTwo.valueOf(model.getCategorySecond()));
        }
        if (product.isEmpty()) {
            return null;
        }
        return addedImageAndMapper(product);
    }

    private List<ProductServiceModelView> addedImageAndMapper(List<Product> products) {
        List<ProductServiceModelView> result = new ArrayList<>();
        for (Product product : products) {
            ProductServiceModelView productModel = mapper.map(product, ProductServiceModelView.class);
            productModel.setImage(imageService.findAllByModel(product.getModel()));
            result.add(productModel);
        }
        return result;
    }

    private int getDiscount(Double currentPrice, Double updatePrice) {
        double result = 100 - ((updatePrice / currentPrice) * 100);
        return (int) Math.round(result);
    }

    public ProductServiceModelView mapAndAddingImage(Product product){
        ProductServiceModelView productServiceModelView = mapper.map(product, ProductServiceModelView.class);
        productServiceModelView.setImage(imageService.findAllByModel(product.getModel()));
        return productServiceModelView;
    }

}