package com.example.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account Information."
)
public class AccountsDto {

    @Schema(
            description = "Account No of the customer."
    )
    @NotEmpty(message = "Account number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits.")
    private Long accountNumber;

    @Schema(
            description = "Account type of the customer.",example = "Savings / Current"
    )
    @NotEmpty(message = "Account Type cannot be null or empty.")
    private String accountType;

    @Schema(
            description = "Bank branch address."
    )
    @NotEmpty(message = "Branch Address cannot be null or empty.")
    private String branchAddress;
}
