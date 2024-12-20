package org.amanverma.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.amanverma.restaurant.validation.ValidationGroups.*;

public record CustomerRequest(
        @NotNull(message = "Customer should be present")
        @NotEmpty(message = "Customer should be present")
        @NotBlank(message = "Customer should be present")
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @NotNull(message="Customer email is required")
        @Email(message = "Email must be in correct format")
        @JsonProperty("email")
        String email,

        @NotNull(message = "Password should be present", groups = CreateGroup.class)
        @NotEmpty(message = "Password should be present", groups = CreateGroup.class)
        @NotBlank(message = "Password should be present", groups = CreateGroup.class)
        @Size(min = 6, max = 12)
        @JsonProperty("password")
        String password,

        @NotNull(message = "Address should be present")
        @JsonProperty("address")
        String address,

        @NotNull(message = "City should be present")
        @JsonProperty("city")
        String city,

        @NotNull(message = "Pincode should be present")
        @JsonProperty("pincode")
        String pincode
) {
}
