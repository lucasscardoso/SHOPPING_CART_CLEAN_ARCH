package com.cart.shopping.cart.externals.config;

import com.cart.shopping.cart.core.entity.Cart;
import com.cart.shopping.cart.core.interfaces.feign.IProductFeignClient;
import com.cart.shopping.cart.core.interfaces.feign.IUserFeignClient;
import com.cart.shopping.cart.core.repository.ShoppingCartRepository;
import com.cart.shopping.cart.core.service.SaveShoppingCart;
import com.cart.shopping.cart.core.shared.dto.ShoppingCartDto;
import com.cart.shopping.cart.core.shared.useCase.IShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingCartConfigure {

    @Bean
    public IShoppingCart<Cart, ShoppingCartDto> SaveShoppingCart(
            IProductFeignClient iProductFeignClient,
            IUserFeignClient iUserFeignClient,
            ShoppingCartRepository shoppingCartRepository

    ){
        return new SaveShoppingCart(
                iUserFeignClient,
                iProductFeignClient,
                shoppingCartRepository
                );
    }
}
