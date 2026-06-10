package com.cart.shopping.cart.core.service;

import com.cart.shopping.cart.core.entity.Cart;
import com.cart.shopping.cart.core.interfaces.feign.IProductFeignClient;
import com.cart.shopping.cart.core.interfaces.feign.IUserFeignClient;
import com.cart.shopping.cart.core.repository.ShoppingCartRepository;
import com.cart.shopping.cart.core.shared.dto.ClientDto;
import com.cart.shopping.cart.core.shared.dto.ProductDto;
import com.cart.shopping.cart.core.shared.dto.ShoppingCartDto;
import com.cart.shopping.cart.core.shared.dto.ShoppingCartRequest;
import com.cart.shopping.cart.core.shared.useCase.IGetShoppingCart;
import com.cart.shopping.cart.core.shared.useCase.IShoppingCart;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


public class GetShoppingCart implements IGetShoppingCart {

    private IProductFeignClient iProductFeign;
    private IUserFeignClient userFeign;
    private ShoppingCartRepository shoppingCartRepository;

    public GetShoppingCart(IUserFeignClient userFeign, IProductFeignClient iProductFeign, ShoppingCartRepository shoppingCartRepository) {
        this.userFeign = userFeign;
        this.iProductFeign = iProductFeign;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Transactional(readOnly = true)
    public ShoppingCartDto executar(ShoppingCartRequest entrada) {

            ClientDto user = userFeign.findById(entrada.clientId());
            try{
            if(user == null) {throw new IllegalArgumentException("Cliente não localizado com id:" + entrada.clientId()); }
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }

          List<Cart> allClientItens = shoppingCartRepository.findAllByClientId(entrada.clientId());

            if(allClientItens.isEmpty()){
                return new ShoppingCartDto(null, user, List.of(), 0.0);
            }

            List<ProductDto> listaProdutos = allClientItens.stream().map( item -> {
                try{
                    return iProductFeign.findById(item.getProdId());
                } catch (Exception e) {
                    return null;
                }
            }).filter(Objects::nonNull).toList();

            Double valorTotalCarrinho = allClientItens.stream()
                .mapToDouble(Cart::getTotalValue)
                .sum();

            Long cartId = allClientItens.get(0).getCartId();

            return new ShoppingCartDto(cartId,user,listaProdutos,valorTotalCarrinho);


    }



}
