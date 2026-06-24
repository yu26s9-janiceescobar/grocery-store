package org.yearup.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ShoppingCart {
    private Long userId;
    private List<CartItem> items;

    public ShoppingCart(Long userId){
        this.userId = userId;
        this.items = new ArrayList<>();
    }

    public Long getUserId(){
        return userId;
    }
    public List<CartItem> getItems(){
        return items;
    }
    public void setItems(List<CartItem> items){
        this.items = items;
    }
    public BigDecimal getTotal()
    {
        return items.stream().map(CartItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }





}
