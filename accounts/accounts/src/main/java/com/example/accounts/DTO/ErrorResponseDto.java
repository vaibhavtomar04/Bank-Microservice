package com.example.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response Information."
)
@Data @AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "stores the api path which is giving the error"
    )
    private String apiPath;

    @Schema(
            description = "Error code in the response."
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error Message in the response."
    )
    private String errorMessage;

    @Schema(
            description = "Timestamp of the erro."
    )
    private LocalDateTime timestamp;
}
