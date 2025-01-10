package team7.delivery.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team7.delivery.dto.order.CreateOrderRequestDto;
import team7.delivery.dto.order.OrderResponseDto;
import team7.delivery.entity.Order;
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
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequestDto requestDto){

        log.info("userid:{} , menuId : {} ", requestDto.getUserId(),requestDto.getMenuId());
        Order order = orderService.createOrder(requestDto.getUserId(), requestDto.getMenuId());

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
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        Order orderStatus = orderService.updateOrderStatus(id, status);
        return new ResponseEntity<>(orderStatus, HttpStatus.OK);
    }

}
