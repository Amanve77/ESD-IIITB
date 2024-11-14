package org.amanverma.restaurant.mapper;

import org.amanverma.restaurant.entity.Product;
import org.amanverma.restaurant.dto.ProductRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }


}
