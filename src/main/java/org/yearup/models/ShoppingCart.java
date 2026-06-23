package org.yearup.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public ShoppingCart(Long id, User user){
        this.id = id;
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

    public void addCartItem(CartItem cartItem)
    {
        cartItems.add(cartItem);
    }
    public void deleteCartItem(CartItem cartItem){
        cartItems.remove(cartItem);
    }

}
