package com.cart.shopping.cart.externals.controllers;


import com.cart.shopping.cart.core.shared.dto.ShoppingCartDto;
import com.cart.shopping.cart.core.shared.interfaces.IDeleteShoppingCart;
import com.cart.shopping.cart.core.shared.interfaces.IGetShoppingCart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delete")
public class DeleteCartController {

    private final IDeleteShoppingCart shoppingCartDto;


    public DeleteCartController(IDeleteShoppingCart shoppingCartDto) {
        this.shoppingCartDto = shoppingCartDto;
    }


    @DeleteMapping ("/{id}")
    public ResponseEntity<ShoppingCartDto> getCart(@PathVariable  Long id){
        System.out.println("passou aqui o delete carrinho");
        shoppingCartDto.executar(id);
        System.out.println("Carrinho deletado com sucesso!");
        return ResponseEntity.ok(new ShoppingCartDto(null,null,null,0.0));
    }
}
