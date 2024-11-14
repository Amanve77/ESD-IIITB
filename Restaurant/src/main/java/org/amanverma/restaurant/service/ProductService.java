package org.amanverma.restaurant.service;

import org.amanverma.restaurant.dto.ProductRequest;
import org.amanverma.restaurant.entity.Customer;
import org.amanverma.restaurant.entity.Product;
import org.amanverma.restaurant.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import org.amanverma.restaurant.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    private ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepo productRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }


    public String createProduct(ProductRequest request) {
        Product product = productMapper.toEntity(request);
        productRepo.save(product);
        return "Product Created Successfully";
    }
}
