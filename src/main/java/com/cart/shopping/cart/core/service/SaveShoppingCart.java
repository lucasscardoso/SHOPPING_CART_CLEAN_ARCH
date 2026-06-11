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
import java.util.Optional;


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

          //  List<Cart> itensExistentes = shoppingCartRepository.findAllByClientId(entrada.clientId());

            for (CartItemRequest itemRequest : entrada.itens()) {
                ProductDto produto = iProductFeign.findById(itemRequest.prodId());

                Optional<Cart> itensExistentes = shoppingCartRepository.findByClientIdAndProdId(entrada.clientId(), itemRequest.prodId());
                Cart itemSubstituto = itensExistentes.stream()
                        .filter(cart -> cart.getProdId().equals(itemRequest.prodId()))
                        .findFirst()
                        .orElse(null);

                Cart cartRedis;

                if (itemSubstituto != null) {

                    cartRedis = itemSubstituto;
                    int novaQuantidade = cartRedis.getQuantity() + itemRequest.quantity();
                    cartRedis.setQuantity(novaQuantidade);
                    cartRedis.setTotalValue(produto.preco() * novaQuantidade);
                } else {
                    cartRedis = new Cart();
                    cartRedis.setClientId(entrada.clientId());
                    cartRedis.setProdId(itemRequest.prodId());
                    cartRedis.setQuantity(itemRequest.quantity());
                    cartRedis.setTotalValue(produto.preco() * itemRequest.quantity());
                }
                shoppingCartRepository.save(cartRedis);
            }

            List<Cart> allClientItens = shoppingCartRepository.findAllByClientId(entrada.clientId());

            List<ProductDto> listaProdutos = allClientItens.stream()
                    .map(item -> {
                        ProductDto produtoFeign = iProductFeign.findById(item.getProdId());

                        if(produtoFeign == null) return null;
                            return new ProductDto(
                                    produtoFeign.id(),
                                    produtoFeign.nome(),
                                    item.getQuantity(),
                                    produtoFeign.preco()
                            );

                    })
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