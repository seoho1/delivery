package team7.delivery.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team7.delivery.dto.order.OrderResponseDto;
import team7.delivery.entity.Menu;
import team7.delivery.entity.Order;
import team7.delivery.entity.Store;
import team7.delivery.entity.User;
import team7.delivery.exception.ApiException;
import team7.delivery.exception.ExceptionUtil;
import team7.delivery.exception.util.ErrorMessage;
import team7.delivery.repository.MenuRepository;
import team7.delivery.repository.OrderRepository;
import team7.delivery.repository.UserRepository;
import team7.delivery.status.OrderStatus;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public Order createOrder(Long userId, Long menuId) {

        User user = userRepository.findById(userId).orElseThrow(()-> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));
        Menu menu = menuRepository.findById(menuId).orElseThrow(()-> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));

        Store store = menu.getStore();

        // 최소 금액 확인
        if(menu.getPrice() < store.getMinPrice()) {
            throw ExceptionUtil.throwErrorMessage(ErrorMessage.MINIMUM_ORDER_AMOUNT_NOT_MET, ApiException.class);
        }

        //TODO store -> OpenTime, CloseTime LocalDateTime 으로 변경되면 주석해제!

//         가게 오픈/마감시간 확인
//        LocalDateTime now = LocalDateTime.now();
//        if (now.isBefore(store.getOpenTime()) || now.isAfter(store.getCloseTime())) {
//            throw ExceptionUtil.throwErrorMessage(ErrorMessage.STORE_CLOSED, ApiException.class);
//        }

        return orderRepository.save(new Order(user, menu));
    }

    public OrderResponseDto getOrderStatus(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));
        return OrderResponseDto.of(order);
    }

    @Transactional
    public Order updateOrderStatus(Long id, OrderStatus newStatus) {
        Order order = orderRepository.findById(id).orElseThrow(()-> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));
        if(!canUpdateStatus(order.getStatus(), newStatus)) {
            throw ExceptionUtil.throwErrorMessage(ErrorMessage.INVALID_STATUS, ApiException.class);
        }
        return orderRepository.save(new Order(newStatus, order));
    }

    public boolean canUpdateStatus(OrderStatus currentStatus, OrderStatus newStatus) {
        switch (currentStatus) {
            case PENDING -> {
                return newStatus == OrderStatus.PENDING;
            }
            case PREPARING -> {
                return newStatus == OrderStatus.DELIVERING;
            }
            case DELIVERING -> {
                return newStatus == OrderStatus.COMPLETED;
            }
            default -> {
                return false;
            }
        }
    }
}
