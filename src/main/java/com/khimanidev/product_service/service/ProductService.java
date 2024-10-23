package com.khimanidev.product_service.service;

import com.khimanidev.product_service.dto.ProductRequest;
import com.khimanidev.product_service.dto.ProductResponse;
import com.khimanidev.product_service.model.Product;
import com.khimanidev.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //removes the constructor injection boilerplate in line 13
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    /* public ProductService(ProductRepository productRepository) {this.productRepository = productRepository;
    }*/

public ProductResponse createProduct(ProductRequest productRequest){
    Product product=Product.builder()
            .name(productRequest.name())  //ProductRequest record allows to call the method by the field name instead of involving get methods ie getName
            .description(productRequest.description())
            .price(productRequest.price())
            .build();

       productRepository.save(product); //saves the product in database
       log.info("Product saved Successfully");
       return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice());
  }

    public List<ProductResponse> getAllProduct() {
    return  productRepository.findAll()
            .stream()
            .map(product->new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice()))
            .toList();
    }
}
