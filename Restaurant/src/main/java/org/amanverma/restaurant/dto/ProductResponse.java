package org.amanverma.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponse(

        @JsonProperty("product_name")
        String name,
        @JsonProperty("price")
        Double price

) {
}
