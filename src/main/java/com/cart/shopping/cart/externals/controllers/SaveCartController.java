package com.cart.shopping.cart.externals.controllers;


import com.cart.shopping.cart.core.shared.dto.SaveCartRequest;
import com.cart.shopping.cart.core.shared.dto.ShoppingCartDto;
import com.cart.shopping.cart.core.shared.interfaces.ISaveShoppingCart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/save")
public class SaveCartController {

    private final ISaveShoppingCart shoppingCartDto;


    public SaveCartController(ISaveShoppingCart shoppingCartDto) {
        this.shoppingCartDto = shoppingCartDto;
    }


    @PostMapping()
    public ShoppingCartDto saveCart(@RequestBody SaveCartRequest domain){
        System.out.println("passou aqui");
       return shoppingCartDto.executar(domain);
    }
}
