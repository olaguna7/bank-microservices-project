package com.oscar.accountsms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Response", description = "Schema to hold API successfully responses information")
@Data
@AllArgsConstructor
public class ResponseDTO {

    @Schema(description = "Status code in the response")
    private String statusCode;

    @Schema(description = "Status code in the response")
    private String statusMessage;

}
