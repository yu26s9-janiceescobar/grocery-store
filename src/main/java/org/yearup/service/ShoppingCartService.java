package org.yearup.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.yearup.exception.ResourceNotFoundException;
import org.yearup.models.*;
import org.yearup.repository.ProductRepository;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService
{
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository)
    {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
    }

    public ShoppingCart getCartByUserId(Long userId)
    {
       List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);

       ShoppingCart shoppingCart = new ShoppingCart(userId);

       cartItems.forEach(shoppingCart::addItem);

       return shoppingCart;
    }
    @Transactional
    public ShoppingCart updateProductQuantity(Long userId, Long productId, int quantity){
        CartItem cartItem = shoppingCartRepository.findByUserIdAndProduct_ProductId(userId, productId).orElseThrow(()-> new ResourceNotFoundException("Product Does Not Exist In Cart."));
        cartItem.setQuantity(quantity);
        shoppingCartRepository.save(cartItem);
        return  getCartByUserId(userId);

    }
    @Transactional
    public ShoppingCart addProductToCart(Long userId, Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product Not Found: " + productId));

        Optional<CartItem> existing = shoppingCartRepository.findByUserIdAndProduct_ProductId(userId, productId);
        existing.ifPresentOrElse(
                item -> {
                    item.setQuantity(item.getQuantity() + 1);
                shoppingCartRepository.save(item);
                },
                () -> shoppingCartRepository.save(new CartItem(userId, product))
                );
        return getCartByUserId(userId);
    }

    @Transactional
    public ShoppingCart clearCart(Long userId){
        shoppingCartRepository.deleteByUserId(userId);
        return getCartByUserId(userId);
    }

}
