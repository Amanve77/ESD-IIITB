package org.amanverma.restaurant.controller;

import org.amanverma.restaurant.dto.CustomerResponse;
import org.amanverma.restaurant.dto.ProductRequest;
import org.amanverma.restaurant.dto.ProductResponse;
import org.amanverma.restaurant.entity.Product;
import org.amanverma.restaurant.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {

        ProductResponse product = productService.getProductById(id);

        return ResponseEntity.ok(product);
    }

}
