package team7.delivery.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team7.delivery.dto.order.OrderResponseDto;
import team7.delivery.entity.Menu;
import team7.delivery.entity.Order;
import team7.delivery.entity.User;
import team7.delivery.exception.ApiException;
import team7.delivery.exception.ExceptionUtil;
import team7.delivery.exception.util.ErrorMessage;
import team7.delivery.repository.MenuRepository;
import team7.delivery.repository.OrderRepository;
import team7.delivery.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;


    public Order createOrder(Long userId, Long menuId) {

        User user = userRepository.findById(userId).orElseThrow(()-> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));
        Menu menu = menuRepository.findById(menuId).orElseThrow(()-> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));

        return orderRepository.save(new Order(user, menu));
    }

    public OrderResponseDto getOrderStatus(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));
        return OrderResponseDto.of(order);
    }
}
