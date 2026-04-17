package com.cart.shopping.cart.core.service;

import com.cart.shopping.cart.core.entity.Cart;
import com.cart.shopping.cart.core.interfaces.feign.IProductFeignClient;
import com.cart.shopping.cart.core.interfaces.feign.IUserFeignClient;
import com.cart.shopping.cart.core.repository.ShoppingCartRepository;
import com.cart.shopping.cart.core.shared.dto.ClientDto;
import com.cart.shopping.cart.core.shared.dto.ProductDto;
import com.cart.shopping.cart.core.shared.dto.ShoppingCartDto;
import com.cart.shopping.cart.core.shared.useCase.IShoppingCart;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


public class SaveShoppingCart implements IShoppingCart<Cart, ShoppingCartDto> {

    private IProductFeignClient iProductFeign;
    private IUserFeignClient userFeign;
    private ShoppingCartRepository shoppingCartRepository;

    public SaveShoppingCart(IUserFeignClient userFeign, IProductFeignClient iProductFeign, ShoppingCartRepository shoppingCartRepository) {
        this.userFeign = userFeign;
        this.iProductFeign = iProductFeign;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    @Transactional
    public ShoppingCartDto executar(Cart entrada) {

        try {
            ClientDto user = userFeign.findById(entrada.getClientId());
            ProductDto produto = iProductFeign.findById(entrada.getProdId());
            Double valorTotal = produto.preco() * entrada.getQuantity();


            Cart cartRedis = new Cart();

            cartRedis.setClientId(entrada.getClientId());
            cartRedis.setProdId(entrada.getProdId());
            cartRedis.setQuantity(entrada.getQuantity());
            cartRedis.setTotalValue(valorTotal);

            Cart carrinho = shoppingCartRepository.save(cartRedis);

            List<Cart> allClientItens = shoppingCartRepository.findAllByClientId(entrada.getClientId());

            List<ProductDto> listaProdutos = allClientItens.stream()
                    .map(item -> iProductFeign.findById(item.getProdId()))
                    .filter(Objects::nonNull)
                    .toList();

            Double valorTotalCarrinho = allClientItens.stream()
                    .mapToDouble(Cart::getTotalValue)
                    .sum();


            return new ShoppingCartDto(carrinho.getCartId(), user, listaProdutos, valorTotalCarrinho);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cliente com id: " + entrada.getClientId() + " ou produto com id: " + entrada.getProdId()+  " não localizados!");
        }

    }
}
