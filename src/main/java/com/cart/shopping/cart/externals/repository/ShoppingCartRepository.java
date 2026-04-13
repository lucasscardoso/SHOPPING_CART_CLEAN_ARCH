package com.cart.shopping.cart.externals.repository;

import com.cart.shopping.cart.externals.entity.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends CrudRepository<CartEntity, Long> {

    Optional<CartEntity> findByClientId(Long clientId);
}
