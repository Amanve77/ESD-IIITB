package org.amanverma.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record LoginRequest(@NotNull(message = "Email is required")
                           @Email(message = "Email must be in a valid format")
                           @JsonProperty("email")
                           String email,

                           @NotNull(message = "Password is required")
                           @Size(min = 6, max = 12)
                           @JsonProperty("password")
                           String password) {
}
