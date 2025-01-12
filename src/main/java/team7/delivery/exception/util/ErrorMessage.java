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
    STORE_NOT_FOUND("가게를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    OWNER_FORBIDDEN("사장님만 가게를 만들 수 있습니다.", HttpStatus.FORBIDDEN),
    MAX_STORE_LIMIT("사장님은 가게를 최대 3개까지만 운영할 수 있습니다.", HttpStatus.BAD_REQUEST),
    FORBIDDEN_UPDATE("가게를 수정할 권한이 없습니다", HttpStatus.FORBIDDEN),
    FORBIDDEN_DELETE("가게를 폐업할 권한이 없습니다.", HttpStatus.NOT_FOUND);


    private final String message;
    private final HttpStatus status;

    ErrorMessage(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
