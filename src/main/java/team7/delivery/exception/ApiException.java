package team7.delivery.exception;

import org.springframework.http.HttpStatus;
import team7.delivery.exception.util.ErrorResponse;

import java.util.List;

public class ApiException extends BaseException {

    public ApiException(String message, HttpStatus status) {
        super(message,status);
    }

    public ApiException(String message, HttpStatus status, List<String> errorField, ErrorResponse response) {
        super(message,status,errorField,response);
    }

}
