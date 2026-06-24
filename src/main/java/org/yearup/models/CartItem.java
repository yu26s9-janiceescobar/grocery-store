package org.yearup.models;

import jakarta.persistence.*;

@Entity
@Table(name = "shopping_cart")
public class CartItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_item_id")
    private ShoppingCart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity = 1;

    public CartItem(){}
    public CartItem(ShoppingCart cart, Product product){
        this.cart = cart;
        this.product = product;
    }
    public Long getId()
    {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingCart getCart(){
        return cart;
    }

    public void setCart(ShoppingCart cart){
        this.cart = cart;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
