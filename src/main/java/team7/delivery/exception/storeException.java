package team7.delivery.exception;

import org.springframework.http.HttpStatus;

//@AllArgsConstructor
public class StoreException extends RuntimeException {
    private String message;
    private HttpStatus httpstatus;

    public StoreException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpstatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpstatus;
    }
}