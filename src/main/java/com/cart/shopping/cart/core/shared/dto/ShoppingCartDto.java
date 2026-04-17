package com.cart.shopping.cart.core.shared.dto;

import java.util.List;

public record ShoppingCartDto(Long cart_id,
                              ClientDto client,
                              List<ProductDto> productList,
                              Double valor_total){}
