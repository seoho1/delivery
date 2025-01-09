package team7.delivery.exception;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 특정 Enum 클래스에 값이 포함되어 있는지 검증하기 위한 커스텀 어노테이션입니다.
 * 이 어노테이션은 필드나 메서드 매개변수에 적용할 수 있습니다.
 * - @Target: 어노테이션을 적용할 수 있는 위치를 지정 (필드, 매개변수).
 * - @Retention: 런타임 동안 어노테이션이 유지됨을 나타냄.
 * - @Constraint: 이 어노테이션과 연관된 커스텀 검증기 클래스를 지정.
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER }) // 필드와 메서드 매개변수에서 사용 가능
@Retention(RetentionPolicy.RUNTIME) // 런타임 시점에도 유지됨
@Constraint(validatedBy = EnumValidatorImpl.class)  // 커스텀 검증 로직 구현 클래스 지정
public @interface EnumValidator {
    String message() default "Invalid value for enum";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends Enum<?>> enumClass();


}
