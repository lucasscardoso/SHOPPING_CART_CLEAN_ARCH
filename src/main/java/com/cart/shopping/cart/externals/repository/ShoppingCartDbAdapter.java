package com.cart.shopping.cart.externals.repository;

import com.cart.shopping.cart.core.entity.Cart;
import com.cart.shopping.cart.core.repository.ShoppingCartRepository;
import com.cart.shopping.cart.externals.entity.CartEntity;
import com.cart.shopping.cart.externals.interfaces.dbInterfaces.IShoppingCartRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ShoppingCartDbAdapter implements ShoppingCartRepository {

    private final IShoppingCartRepository redisRepository;

    public ShoppingCartDbAdapter(IShoppingCartRepository redisRepository ) {
        this.redisRepository = redisRepository;
    }

    public List<Cart> findAllByClientId(Long id){
        return redisRepository.findAllByClientId(id).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Cart> findByClientIdAndProdId(Long clientId, Long prodId) {
        return redisRepository.findByClientIdAndProdId(clientId,prodId).map(this::toDomain);
    }


    @Override
    public void removerItem(Long cartId) {

    }

    @Override
    public void limpaCart(Long clientId) {

    }

    @Override
    public Cart save(Cart cart){
        CartEntity entity = toEntity(cart);

        if (cart.getCartId() == null) {
            entity.setCartId((cart.getClientId() * 1000000L) + cart.getProdId());
        } else {
            entity.setCartId(cart.getCartId());
            redisRepository.deleteById(cart.getCartId());
        }

        redisRepository.save(entity);
        Cart response = toDomain(entity);
        return response;
    }




    private Cart toDomain(CartEntity entity) {
        Cart cart = new Cart();
        cart.setCartId(entity.getCartId());
        cart.setClientId(entity.getClientId());
        cart.setProdId(entity.getProdId());
        cart.setQuantity(entity.getQuantity());
        cart.setTotalValue(entity.getTotalValue());
        return cart;
    }

    private CartEntity toEntity(Cart domain) {
        CartEntity entity = new CartEntity();
        entity.setCartId(domain.getCartId());
        entity.setCartId(domain.getCartId());
        entity.setClientId(domain.getClientId());
        entity.setProdId(domain.getProdId());
        entity.setQuantity(domain.getQuantity());
        entity.setTotalValue(domain.getTotalValue());
        return entity;
    }


}
