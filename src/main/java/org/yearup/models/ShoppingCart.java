package org.yearup.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ShoppingCart {
    private Long userId;
    private List<CartItem> cartItems;

    public ShoppingCart(Long userId){
        this.userId = userId;
        this.cartItems = new ArrayList<>();
    }

    public Long getUserId(){
        return userId;
    }
    public List<CartItem> getCartItems(){
        return cartItems;
    }
    public void setCartItems(List<CartItem> cartItems){
        this.cartItems = cartItems;
    }
   
//    public boolean containsProduct(Long productId){
//        return cartItems.stream().anyMatch(item -> item.getProduct().getProductId().equals(productId));
//    }
//    public Optional<CartItem> getCartItem(Long productId){
//        return cartItems.stream()
//                .filter(item -> item.getProduct().getProductId().equals(productId)).findFirst();
//    }



}
