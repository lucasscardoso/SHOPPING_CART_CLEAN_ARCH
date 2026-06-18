package com.cart.shopping.cart.externals.config;

import com.cart.shopping.cart.core.interfaces.feign.IProductFeignClient;
import com.cart.shopping.cart.core.interfaces.feign.IUserFeignClient;
import com.cart.shopping.cart.core.repository.ShoppingCartRepository;
import com.cart.shopping.cart.core.service.useCase.DeleteShoppingCart;
import com.cart.shopping.cart.core.service.useCase.GetShoppingCart;
import com.cart.shopping.cart.core.service.useCase.SaveShoppingCart;
import com.cart.shopping.cart.core.shared.interfaces.IDeleteShoppingCart;
import com.cart.shopping.cart.core.shared.interfaces.IGetShoppingCart;
import com.cart.shopping.cart.core.shared.interfaces.ISaveShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingCartConfigure {

    @Bean
    public ISaveShoppingCart SaveShoppingCart(
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

    @Bean
    public IGetShoppingCart GetShoppingCart(
            IProductFeignClient iProductFeignClient,
            IUserFeignClient iUserFeignClient,
            ShoppingCartRepository shoppingCartRepository

    ){
        return new GetShoppingCart(
                iUserFeignClient,
                iProductFeignClient,
                shoppingCartRepository
        );
    }

    @Bean
    public IDeleteShoppingCart DeleteShoppingCart(
            IUserFeignClient iUserFeignClient,
            ShoppingCartRepository shoppingCartRepository

    ){
        return new DeleteShoppingCart(
                iUserFeignClient,
                shoppingCartRepository
        );
    }
}
