package com.cart.shopping.cart.externals.entity;


import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@RedisHash(value = "Cart", timeToLive = 60)
public class CartEntity implements Serializable {

    @Id
    private Long cartId;
    
    private Long prodId;
    @Indexed
    private Long clientId;
    
    private Integer quantity;
    
    private Double totalValue;

    public CartEntity() {
    }

    public CartEntity(Long cartId, Long prodId, Long clientId, Integer quantity, Double totalValue) {
        this.cartId = cartId;
        this.prodId = prodId;
        this.clientId = clientId;
        this.quantity = quantity;
        this.totalValue = totalValue;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}
