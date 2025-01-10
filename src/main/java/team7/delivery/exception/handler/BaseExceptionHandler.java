package team7.delivery.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import team7.delivery.exception.BaseException;
import team7.delivery.exception.util.ErrorMessageGenerator;
import team7.delivery.exception.util.ErrorResponse;
import team7.delivery.exception.ExceptionUtil;
import team7.delivery.exception.util.ValidationErrorCode;

import java.util.List;

@ControllerAdvice
public class BaseExceptionHandler {

    // 공통 예외 처리 로직
    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ErrorResponse> handleException(BaseException e, List<String> validFields) {
        List<String> errorField = e.getErrorField();

        if (errorField != null && !errorField.isEmpty()) {
            return setErrorResponseForValidation(e, errorField, validFields);  // 공통 필터링 및 에러 처리
        }

        return ExceptionUtil.GenerateResponseEntity(e.getStatus().value(), e.getMessage(), e.getStatus());
    }

    // 필드에 맞는 에러 메시지 처리
    private ResponseEntity<ErrorResponse> setErrorResponseForValidation(BaseException e, List<String> errorField, List<String> validFields) {
        int combinedErrorCode = 0;
        // errorField에 따라 비트 연산으로 오류 코드 합산
        for (String field : errorField) {
            if (validFields.contains(field)) {  // 필터링된 필드만 처리
                switch (field) {
                    case "id":
                        combinedErrorCode |= ValidationErrorCode.INVALID_ID.getCode();
                        break;
                    case "password":
                        combinedErrorCode |= ValidationErrorCode.INVALID_PASSWORD.getCode();
                        break;
                    case "email":
                        combinedErrorCode |= ValidationErrorCode.INVALID_EMAIL.getCode();
                        break;
                    case "review":
                        combinedErrorCode |= ValidationErrorCode.INVALID_REVIEW.getCode();
                        break;
                    default:
                        break;
                }
            }
        }

        // 비트 연산된 오류 코드로 메시지 생성
        String errorMessage = ErrorMessageGenerator.generateErrorMessage(combinedErrorCode);
        return ExceptionUtil.GenerateResponseEntity(e.getStatus().value(), errorMessage, combinedErrorCode, e.getStatus());
    }
}
