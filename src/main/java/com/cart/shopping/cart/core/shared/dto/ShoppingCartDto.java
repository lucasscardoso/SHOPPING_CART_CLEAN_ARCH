package com.cart.shopping.cart.core.shared.dto;

import java.util.List;

public record ShoppingCartDto(Long cart_id,
                              ClientDto clientDto,
                              List<ProductDto>productDtoList,
                              Double valor_total){}
