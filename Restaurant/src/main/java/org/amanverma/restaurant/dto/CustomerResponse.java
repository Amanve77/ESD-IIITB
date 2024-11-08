package org.amanverma.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerResponse (
        @JsonProperty("First_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        @JsonProperty("email")
        String email
) {
}
