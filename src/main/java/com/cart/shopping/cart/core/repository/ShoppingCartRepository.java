package com.cart.shopping.cart.core.repository;

import com.cart.shopping.cart.core.entity.Cart;
import com.cart.shopping.cart.externals.entity.CartEntity;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository {

    Cart save(Cart cart);
    List<Cart> findAllByClientId(Long clientId);
    Optional<Cart> findByClientIdAndProdId(Long clientId, Long prodId);
    void removerItem(Long cartId);
    void limpaCart(Long clientId);
}
