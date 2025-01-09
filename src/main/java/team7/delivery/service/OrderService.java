package team7.delivery.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team7.delivery.dto.order.OrderResponseDto;
import team7.delivery.entity.Menu;
import team7.delivery.entity.Order;
import team7.delivery.entity.User;
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

        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("userId not found"));
        Menu menu = menuRepository.findById(menuId).orElseThrow(()-> new IllegalArgumentException("menuId not found"));
        Order order = new Order();
        order.setUser(user);
        order.setMenu(menu);

        return orderRepository.save(order);
    }

    public OrderResponseDto getOrderStatus(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("order not found"));
        return OrderResponseDto.of(order);
    }
}
