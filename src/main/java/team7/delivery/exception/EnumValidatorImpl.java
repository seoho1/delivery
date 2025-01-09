package team7.delivery.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * EnumValidator 어노테이션의 검증 로직을 구현하는 클래스입니다.
 * 이 클래스는 특정 문자열이 지정된 Enum 클래스의 값으로 유효한지 확인합니다.
 * - @ConstraintValidator: ConstraintValidator 인터페이스를 구현하여 커스텀 검증 로직을 정의.
 */
public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

    private Class<? extends Enum<?>> enumClass;

    /**
     * 초기화 메서드. EnumValidator 어노테이션에서 전달받은 Enum 클래스를 설정합니다.
     *
     * @param constraintAnnotation EnumValidator 어노테이션 객체
     */

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    /**
     * 검증 로직을 구현하는 메서드입니다.
     * 주어진 값이 지정된 Enum 클래스의 유효한 값인지 확인합니다.
     *
     * @param value 검증 대상 값
     * @param constraintValidatorContext 검증 컨텍스트
     * @return 값이 유효하면 true, 유효하지 않으면 false
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }

        try {
            // Enum.valueOf를 사용해 주어진 값이 Enum 값에 해당하는지 확인
            Enum.valueOf((Class<Enum>) enumClass.asSubclass(Enum.class), value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
