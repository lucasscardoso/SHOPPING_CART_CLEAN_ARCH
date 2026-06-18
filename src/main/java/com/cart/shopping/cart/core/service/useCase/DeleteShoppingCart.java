package com.cart.shopping.cart.core.service.useCase;

import com.cart.shopping.cart.core.entity.Cart;
import com.cart.shopping.cart.core.interfaces.feign.IUserFeignClient;
import com.cart.shopping.cart.core.repository.ShoppingCartRepository;
import com.cart.shopping.cart.core.shared.dto.ClientDto;
import com.cart.shopping.cart.core.shared.dto.ShoppingCartDto;
import com.cart.shopping.cart.core.shared.interfaces.IDeleteShoppingCart;

import java.util.List;

public class DeleteShoppingCart implements IDeleteShoppingCart {

    private IUserFeignClient userFeign;
    private ShoppingCartRepository shoppingCartRepository;

    public DeleteShoppingCart(IUserFeignClient userFeign, ShoppingCartRepository shoppingCartRepository) {
        this.userFeign = userFeign;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingCartDto executar(Long entrada) {

        ClientDto user = userFeign.findById(entrada);
        try{
            if(user == null) {throw new IllegalArgumentException("Cliente não localizado com id:" + user.id()); }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }

        List<Cart> allClientItens = shoppingCartRepository.findAllByClientId((user.id()));

        if(allClientItens.isEmpty()){
            return null;
        }

        shoppingCartRepository.limpaCart(user.id());

        return new ShoppingCartDto(null, user,null,0.0);
    }
}
