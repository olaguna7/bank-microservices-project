package com.oscar.accountsms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(name = "Account", description = "Schema to hold Account information")
@Data
public class AccountDTO {

    @Schema(description = "Account number of the user")
    @NotEmpty(message = "Account number can't be a null or empty value")
    @Pattern(regexp = "^$|\\d{10}", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(description = "Account type")
    @NotEmpty(message = "Account type can't be a null or empty value")
    private String accountType;

    @Schema(description = "Branch address for the bank account")
    @NotEmpty(message = "Branch address can't be a null or empty value")
    private String branchAddress;

}
