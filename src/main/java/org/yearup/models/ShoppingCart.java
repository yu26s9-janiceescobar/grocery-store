package org.yearup.models;

import jakarta.persistence.*;
import org.yearup.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
    public ShoppingCart(){}

    public ShoppingCart(User user){
        this.user = user;
        cartItems = new ArrayList<>();
    }

    public Long getId(){
        return id;
    }

    public User getUser(){
        return user;
    }

    public List<CartItem> getCartItems(){
        return cartItems;
    }
    public void setCartItems(List<CartItem> cartItems){
        this.cartItems = cartItems;
    }
    public boolean containsProduct(Long productId){
        return cartItems.stream().anyMatch(item -> item.getId().equals(productId));
    }
    public Optional<CartItem> getCartItem(Long productId){
        return cartItems.stream()
                .filter(item -> item.getProduct().getProductId().equals(productId)).findFirst();
    }

    public void addCartItem(CartItem cartItem)
    {
        cartItems.add(cartItem);
    }
    public void deleteCartItem(CartItem cartItem){
        cartItems.remove(cartItem);
    }

}
