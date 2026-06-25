package org.yearup.models;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class ShoppingCart {
    private final Long userId;
    private Map<Long, CartItem> items = new HashMap<>();

    public ShoppingCart(Long userId){
        this.userId = userId;
    }

    public Long getUserId(){
        return userId;
    }
    public Map<Long, CartItem> getItems() { return items; }
    public void setItems(Map<Long, CartItem> items) { this.items = items; }
    public void addItem(CartItem item) {
        items.put(item.getProduct().getProductId(), item);
    }
    public BigDecimal getTotal()
    {
        return items.values().stream().map(CartItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }





}
