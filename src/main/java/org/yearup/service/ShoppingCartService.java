package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.exception.ResourceNotFoundException;
import org.yearup.models.*;
import org.yearup.repository.ProductRepository;
import org.yearup.repository.ProfileRepository;
import org.yearup.repository.ShoppingCartRepository;
import org.yearup.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService
{
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, UserRepository userRepository)
    {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public ShoppingCart getCartByUserId(Long userId)
    {
       return shoppingCartRepository.findByUserId(userId);
    }
    public ShoppingCart addProductToCart(Long userId, Long productId){
        if (userRepository.existsById(userId)){
            throw new ResourceNotFoundException("User Not Found: " + userId);
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product Not Found: " + productId));

        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);

        Optional<CartItem> existing = shoppingCart.getCartItem(productId);
        existing.ifPresentOrElse(
                item -> item.setQuantity(item.getQuantity() + 1),
                () -> shoppingCart.addCartItem(new CartItem(shoppingCart, product))
        );

        return shoppingCart;
    }

    // add additional methods here
}
