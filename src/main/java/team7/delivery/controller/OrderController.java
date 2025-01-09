package team7.delivery.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team7.delivery.dto.order.CreateOrderRequestDto;
import team7.delivery.entity.Order;
import team7.delivery.service.OrderService;

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
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequestDto requestDto){

        log.info("userid:{} , menuId : {} ", requestDto.getUserId(),requestDto.getMenuId());
        Order order = orderService.createOrder(requestDto.getUserId(), requestDto.getMenuId());

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
