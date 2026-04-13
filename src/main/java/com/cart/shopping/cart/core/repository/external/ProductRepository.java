package com.cart.shopping.cart.core.repository.external;

import com.cart.shopping.cart.core.shared.dto.ProductDto;

import java.util.Optional;

public interface ProductRepository {

    Optional<ProductDto> findById(Long id);
}
