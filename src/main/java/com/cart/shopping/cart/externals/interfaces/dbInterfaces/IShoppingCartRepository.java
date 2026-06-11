package com.cart.shopping.cart.externals.interfaces.dbInterfaces;



import com.cart.shopping.cart.externals.entity.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IShoppingCartRepository extends CrudRepository<CartEntity, Long> {

    List<CartEntity> findAllByClientId(Long clientId);
    Optional<CartEntity> findByClientIdAndProdId(Long clientId, Long prodId);

}
