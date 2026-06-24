package org.yearup.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "shopping_cart")
public class CartItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity = 1;

    public CartItem(){}
    public CartItem(Long userId, Product product){
        this.userId = userId;
        this.product = product;
    }
    public Long getId()
    {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public BigDecimal getLineTotal(){
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
