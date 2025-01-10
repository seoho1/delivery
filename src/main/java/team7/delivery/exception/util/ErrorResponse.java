package team7.delivery.exception.util;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int statusCode;
    private final String massage;
    private int errorCode;

    public ErrorResponse(int statusCode, String massage) {
        this.statusCode = statusCode;
        this.massage = massage;
    }

    public ErrorResponse(int statusCode, String massage, int errorCode) {
        this.statusCode = statusCode;
        this.massage = massage;
        this.errorCode = errorCode;
    }
}
