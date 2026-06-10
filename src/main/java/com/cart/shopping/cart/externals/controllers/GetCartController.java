package com.cart.shopping.cart.externals.controllers;


import com.cart.shopping.cart.core.entity.Cart;
import com.cart.shopping.cart.core.shared.dto.ShoppingCartDto;
import com.cart.shopping.cart.core.shared.dto.ShoppingCartRequest;
import com.cart.shopping.cart.core.shared.useCase.IGetShoppingCart;
import com.cart.shopping.cart.core.shared.useCase.IShoppingCart;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/get")
public class GetCartController {

    private final IGetShoppingCart shoppingCartDto;


    public GetCartController(IGetShoppingCart shoppingCartDto) {
        this.shoppingCartDto = shoppingCartDto;
    }


    @GetMapping("/{id}")
    public ShoppingCartDto getCart(@PathVariable  Long id){
        System.out.println("passou aqui o get carrinho");
        ShoppingCartRequest request = new ShoppingCartRequest(id, null, 0);
       return shoppingCartDto.executar(request);
    }
}
