package ma.ac.emi.bricool.DTO.response;


import lombok.Getter;
import lombok.Setter;
import ma.ac.emi.bricool.DTO.FieldError;

import java.util.List;


@Getter
@Setter
public class ErrorResponse {

    private Integer httpStatus;
    private String exception;
    private String message;
    private List<FieldError> fieldErrors;

}
