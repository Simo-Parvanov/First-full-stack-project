package com.svc.myproject.web.controllers;

import com.svc.myproject.domain.models.services.ProductServiceModel;
import com.svc.myproject.domain.models.services.ProductServiceModelView;
import com.svc.myproject.domain.models.services.ProductServiceUpdateModel;
import com.svc.myproject.repository.ProductRepository;
import com.svc.myproject.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductController(ProductRepository productRepository,
                             ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductServiceModelView>> getAllProduct(){
        return ResponseEntity.ok(productService.allProduct());
    }


    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> createProduct(@RequestBody ProductServiceModel productServiceModel,
                                           UriComponentsBuilder builder){
        productService.createProduct(productServiceModel);
        return ResponseEntity.created(builder.path("/product/create")
                .buildAndExpand().toUri()).build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> update(@PathVariable String id,
                                    @RequestBody ProductServiceUpdateModel updateModel){
        if (productService.findProductById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductServiceModelView productViewModel =
                productService.update(updateModel, id);
        return new ResponseEntity<>(productViewModel, HttpStatus.OK);
    }
}
