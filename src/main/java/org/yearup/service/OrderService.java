package org.yearup.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.yearup.exception.InvalidStateException;
import org.yearup.exception.ResourceNotFoundException;
import org.yearup.models.CartItem;
import org.yearup.models.Order;
import org.yearup.models.OrderLineItem;
import org.yearup.models.Profile;
import org.yearup.repository.OrderLineItemRepository;
import org.yearup.repository.OrderRepository;
import org.yearup.repository.ProfileRepository;
import org.yearup.repository.ShoppingCartRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final ProfileRepository profileRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public OrderService(OrderRepository orderRepository, OrderLineItemRepository orderLineItemRepository, ProfileRepository profileRepository, ShoppingCartRepository shoppingCartRepository){
        this.orderRepository = orderRepository;
        this.orderLineItemRepository = orderLineItemRepository;
        this.profileRepository = profileRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Transactional
    public Order createOrder(Long userId){
        Profile profile = profileRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Profile Not Found."));

        if (profile.getCity().isBlank()
                || profile.getState().isBlank()
                || profile.getAddress().isBlank()
                || profile.getZip().isBlank()
                || profile.getEmail().isBlank()
                || profile.getFirstName().isBlank()
                || profile.getLastName().isBlank()){
            throw new InvalidStateException("Must Fill All Profile Fields");
        }

        List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);
        if (cartItems.isEmpty()){
            throw new InvalidStateException("Shopping Cart is Empty.");
        }

        Order order = new Order(userId,
                LocalDateTime.now(),
                profile.getAddress(),
                profile.getCity(),
                profile.getState(),
                profile.getZip(),
                BigDecimal.ZERO);
        orderRepository.save(order);

        for (CartItem item: cartItems) {
            orderLineItemRepository.save(new OrderLineItem(order.getOrderId(),
                    item.getProduct(),
                    item.getProduct().getPrice(),
                    item.getQuantity(),
                    BigDecimal.ZERO));

        }
        shoppingCartRepository.deleteByUserId(userId);
        return order;
    }
}
