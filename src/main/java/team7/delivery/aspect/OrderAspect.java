package team7.delivery.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class OrderAspect {

    @Pointcut("execution(* team7.delivery.service.OrderService.createOrder(..)) || " +
            "execution(* team7.delivery.service.OrderService.updateOrderStatus(..))")
    public void orderMethods() {}

    @After("orderMethods()")
    public void logOrderAction(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName(); // 메서드 이름

        Object[] args = joinPoint.getArgs();

        // 로그
        if (methodName.equals("createOrder")) {
            Long menuId = (Long) args[0];
            log.info("[{}] 주문 생성 요청 : 메뉴 ID : {}", LocalDateTime.now(), menuId);
        }
        if (methodName.equals("updateOrderStatus")) {
            Long orderId = (Long) args[0];
            String newStatus = args[1].toString();
            log.info("[{}] 주문 상태 변경 요청 : 주문 ID : {}, 새로운 상태 : {}", LocalDateTime.now(),orderId);
        }

    }

}
