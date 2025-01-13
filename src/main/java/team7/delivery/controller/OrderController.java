package team7.delivery.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team7.delivery.dto.order.CreateOrderRequestDto;
import team7.delivery.dto.order.OrderResponseDto;
import team7.delivery.exception.ApiException;
import team7.delivery.exception.ExceptionUtil;
import team7.delivery.exception.util.ErrorMessage;
import team7.delivery.service.OrderService;
import team7.delivery.status.OrderStatus;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    /**
     * 주문 생성
     */
    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody CreateOrderRequestDto requestDto, HttpSession
            httpSession){

        log.info("userid:{} , menuId : {} ", requestDto.getUserId(),requestDto.getMenuId());
        OrderResponseDto order = orderService.createOrder(requestDto.getUserId(),
                requestDto.getMenuId());


        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    /**
     * 주문 상태 조회
     */
    @GetMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> getOrderStatus(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrderStatus(id), HttpStatus.OK);
    }

    /**
     * 주문 상태 변경
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        if (status == null) {
            throw ExceptionUtil.throwErrorMessage(ErrorMessage.INVALID_STATUS, ApiException.class);
        }
        OrderResponseDto orderResponseDto = orderService.updateOrderStatus(id, status);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

}
