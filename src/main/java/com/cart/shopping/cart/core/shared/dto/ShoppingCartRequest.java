package com.cart.shopping.cart.core.shared.dto;

public record ShoppingCartRequest(Long clientId,Long prodId, Integer quantity) {
}
