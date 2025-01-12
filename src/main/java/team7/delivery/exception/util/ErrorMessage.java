package team7.delivery.exception.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMessage {
    ENTITY_NOT_FOUND("선택한 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    PASSWORD_IS_WRONG("비밀번호 입력이 잘못되었습니다.", HttpStatus.UNAUTHORIZED),
    EMAIL_NOT_FOUND("이메일 정보가 존재하지 않습니다.", HttpStatus.UNAUTHORIZED),
    INVALID_SESSIONS("세션이 만료되었습니다.", HttpStatus.UNAUTHORIZED),
    UNCERTIFIED("사용자 인증에 실패했습니다.", HttpStatus.UNAUTHORIZED),
    VALID_ERROR("입력 값이 잘못되었습니다.", HttpStatus.BAD_REQUEST),
    INVALID_STATUS("상태값이 잘못되었습니다.", HttpStatus.UNAUTHORIZED),
    MINIMUM_ORDER_AMOUNT_NOT_MET("최소 주문 금액을 충족하지 못했습니다.", HttpStatus.UNAUTHORIZED),
    STORE_CLOSED("현재 가게는 영업 시간이 아닙니다.", HttpStatus.UNAUTHORIZED);

    private final String message;
    private final HttpStatus status;

    ErrorMessage(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
