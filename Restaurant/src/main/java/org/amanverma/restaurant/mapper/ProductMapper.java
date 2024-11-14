package org.amanverma.restaurant.mapper;

import org.amanverma.restaurant.entity.Product;
import org.amanverma.restaurant.dto.ProductRequest;
import org.springframework.stereotype.Service;
import org.amanverma.restaurant.dto.ProductResponse;

@Service
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }

    public ProductResponse toDto(Product product) {
        return new ProductResponse(
                product.getName(),
                product.getPrice()
        );
    }

}
