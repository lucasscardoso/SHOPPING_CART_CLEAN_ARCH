package com.cart.shopping.cart.externals.interfaces.dbInterfaces;


import com.cart.shopping.cart.externals.entity.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IShoppingCartRepository extends CrudRepository<CartEntity, Long> {

    List<CartEntity> findAllByClientId(Long clientId);
}
