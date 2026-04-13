package com.cart.shopping.cart.core.repository.external;

import com.cart.shopping.cart.core.shared.dto.ClientDto;

import java.util.Optional;

public interface ClientRepository {

    Optional<ClientDto> findById(Long id);
}
