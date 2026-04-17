package com.cart.shopping.cart.externals.controllers;


import com.cart.shopping.cart.core.entity.Cart;
import com.cart.shopping.cart.core.shared.dto.ShoppingCartDto;
import com.cart.shopping.cart.core.shared.useCase.IShoppingCart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/save")
public class SaveCartController {

    private final IShoppingCart<Cart,ShoppingCartDto> shoppingCartDto;


    public SaveCartController(IShoppingCart shoppingCartDto) {
        this.shoppingCartDto = shoppingCartDto;
    }


    @PostMapping()
    public ShoppingCartDto saveCart(@RequestBody  Cart domain){
        System.out.println("passou aqui");
       return shoppingCartDto.executar(domain);
    }
}
