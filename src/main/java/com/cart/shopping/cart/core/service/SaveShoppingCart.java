package com.cart.shopping.cart.core.service;

import com.cart.shopping.cart.core.entity.Cart;
import com.cart.shopping.cart.core.interfaces.feign.IProductFeignClient;
import com.cart.shopping.cart.core.interfaces.feign.IUserFeignClient;
import com.cart.shopping.cart.core.repository.ShoppingCartRepository;
import com.cart.shopping.cart.core.shared.dto.*;
import com.cart.shopping.cart.core.shared.useCase.ISaveShoppingCart;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


public class SaveShoppingCart implements ISaveShoppingCart {

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
    public ShoppingCartDto executar(SaveCartRequest entrada) {

        try {
            ClientDto user = userFeign.findById(entrada.clientId());
            if (user == null) {
                throw new IllegalArgumentException("Cliente com id: " + entrada.clientId() + "Não localizado!");
            }

            for (CartItemRequest itemRequest : entrada.itens()) {
                ProductDto produto = iProductFeign.findById(itemRequest.prodId());
                Double valorTotal = produto.preco() * itemRequest.quantity();

                Cart cartRedis = new Cart();
                cartRedis.setClientId(entrada.clientId());
                cartRedis.setProdId(itemRequest.prodId());
                cartRedis.setQuantity(itemRequest.quantity());
                cartRedis.setTotalValue(valorTotal);

                shoppingCartRepository.save(cartRedis);
            }

            List<Cart> allClientItens = shoppingCartRepository.findAllByClientId(entrada.clientId());

            List<ProductDto> listaProdutos = allClientItens.stream()
                    .map(item -> iProductFeign.findById(item.getProdId()))
                    .filter(Objects::nonNull)
                    .toList();

            Double valorTotalCarrinho = allClientItens.stream()
                    .mapToDouble(Cart::getTotalValue)
                    .sum();

            Long cartId = allClientItens.isEmpty() ? null : allClientItens.get(0).getCartId();

            return new ShoppingCartDto(cartId, user, listaProdutos, valorTotalCarrinho);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar carrinho do cliente: " + entrada.clientId(), e);
        }

    }

    }