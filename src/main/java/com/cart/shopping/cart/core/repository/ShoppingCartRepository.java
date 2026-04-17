package com.cart.shopping.cart.core.repository;

import com.cart.shopping.cart.core.entity.Cart;

import java.util.List;

public interface ShoppingCartRepository {

    Cart save(Cart cart);
    List<Cart> findAllByClientId(Long clientId);
    void removerItem(Long cartId);
    void limpaCart(Long clientId);
}
