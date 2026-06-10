package com.cart.shopping.cart.core.shared.dto;

import java.util.List;

public record SaveCartRequest(Long clientId,
                              List<CartItemRequest> itens) {
}
