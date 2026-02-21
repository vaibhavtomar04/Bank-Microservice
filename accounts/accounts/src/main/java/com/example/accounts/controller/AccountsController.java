package com.example.accounts.controller;


import com.example.accounts.DTO.CustomersDto;
import com.example.accounts.DTO.ErrorResponseDto;
import com.example.accounts.DTO.ResponseDto;
import com.example.accounts.Service.IAccountsService;
import com.example.accounts.constants.AccountsConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD Rest APIs for Accounts in bank",
        description = "CRUD Rest APIs to create , update , fetch and delete accounts details."
)
@RestController
@RequestMapping(path = "/api" ,produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService accountsService;

    @Operation(
            summary = "Create Account REST API",
            description = "Rest Api to create new customer and Accounts inside bank."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created."
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount (@Valid @RequestBody CustomersDto customersDto){
      accountsService.createAccount(customersDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account details REST API",
            description = "Rest Api to fetch account details based on mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK."
    )
    @GetMapping("/getAccountsDetails")
    public ResponseEntity<CustomersDto> fetchAccountDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
                                                                String mobileNumber){
     CustomersDto customersDto = accountsService.getAccountDetails(mobileNumber);
     return ResponseEntity.status(HttpStatus.OK)
                .body(customersDto);
    }


    @Operation(
            summary = "Update Account Details REST API",
            description = "Rest Api to update Accounts details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK."
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status Exception Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema=@Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/updateDetails")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomersDto customersDto){
       boolean isUpdated = accountsService.updateAccount(customersDto);
       if(isUpdated){
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(new ResponseDto(AccountsConstants.STATAUS_200, AccountsConstants.MESSAGE_200));
       }else {
           return ResponseEntity
                   .status(HttpStatus.EXPECTATION_FAILED)
                   .body(new ResponseDto(AccountsConstants.STATAUS_417, AccountsConstants.MESSAGE_417_UPDATE));
       }
    }

    @Operation(
            summary = "Delete Account REST API",
            description = "Rest Api to delete customer and account based on mobile number."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK."
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status Exception Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema=@Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/deleteAccount")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
                                                                String mobileNumber){
        boolean isDeleted = accountsService.DeleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATAUS_200, AccountsConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATAUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

}
