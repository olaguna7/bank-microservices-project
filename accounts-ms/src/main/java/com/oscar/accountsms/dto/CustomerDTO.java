package com.oscar.accountsms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(name = "customer", description = "Schema to hold Customer and Account information")
@Data
public class CustomerDTO {

    @Schema(description = "Name of the customer", example = "John Doe")
    @NotEmpty(message = "Name can not be a null or empty value")
    @Size(min = 5, max = 30, message = "The length og the customer name should be between 5 and 30")
    private String name;

    @Schema(description = "Email of the customer", example = "example@example.com")
    @Email(message = "Email should have a valid format")
    @NotEmpty(message = "Email address can not be a null or empty value")
    private String email;

    @Schema(description = "Mobile number of the customer", example = "1234567890")
    @Pattern(regexp = "^$|\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Account details of the Customer")
    private AccountDTO accountDTO;

}
