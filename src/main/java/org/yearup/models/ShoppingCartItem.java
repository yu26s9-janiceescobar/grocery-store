package org.yearup.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class ShoppingCartItem
{
    private Product product = null;
    private int quantity = 1;
    private double discountPercent = 0;

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
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

    public double getDiscountPercent()
    {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent)
    {
        this.discountPercent = discountPercent;
    }

    @JsonIgnore
    public Long getProductId()
    {
        return product.getProductId();
    }

//    public double getLineTotal()
//    {
//        BigDecimal basePrice = product.getPrice();
//        BigDecimal subTotal = basePrice * this.quantity;
//        BigDecimal discountAmount = subTotal * discountPercent;
//
//        return subTotal - discountAmount;
//    }
}
