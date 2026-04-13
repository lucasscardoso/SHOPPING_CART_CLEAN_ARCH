package com.cart.shopping.cart.core.service;

import com.cart.shopping.cart.core.entity.Cart;
import com.cart.shopping.cart.core.repository.ShoppingCartRepository;
import com.cart.shopping.cart.core.repository.external.ClientRepository;
import com.cart.shopping.cart.core.repository.external.ProductRepository;
import com.cart.shopping.cart.core.shared.dto.ClientDto;
import com.cart.shopping.cart.core.shared.dto.ProductDto;
import com.cart.shopping.cart.core.shared.dto.ShoppingCartDto;
import com.cart.shopping.cart.core.shared.useCase.IShoppingCart;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class SaveShoppingCart implements IShoppingCart<Cart, ShoppingCartDto> {

    private ClientRepository clientRepository;
    private ProductRepository productRepository;
    private ShoppingCartRepository shoppingCartRepository;

    public SaveShoppingCart(ClientRepository clientRepository, ProductRepository productRepository, ShoppingCartRepository shoppingCartRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    @Transactional
    public ShoppingCartDto executar(Cart entrada) {


            ClientDto user = clientRepository.findById(entrada.getClientId()).orElseThrow(() -> new RuntimeException("cliente nao localizado com id: %d" + entrada.getClientId()));
            ProductDto produto = productRepository.findById(entrada.getProdId()).orElseThrow(() -> new RuntimeException("produto nao localizado com id: %d" + entrada.getProdId()));
            Double valorTotal = produto.preco() * entrada.getQuantity();

            Cart cartRedis = new Cart();
            cartRedis.setCartId(entrada.getClientId());
            cartRedis.setClientId(entrada.getClientId());
            cartRedis.setProdId(entrada.getProdId());
            cartRedis.setQuantity(entrada.getQuantity());
            cartRedis.setTotalValue(valorTotal);

            Cart carrinho = shoppingCartRepository.save(cartRedis);


         return new ShoppingCartDto(carrinho.getCartId(), user, List.of(produto),valorTotal);



    }
}
