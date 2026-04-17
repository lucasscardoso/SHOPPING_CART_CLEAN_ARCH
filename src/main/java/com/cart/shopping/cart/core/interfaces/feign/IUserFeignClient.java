package com.cart.shopping.cart.core.interfaces.feign;

import com.cart.shopping.cart.core.shared.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface IUserFeignClient {

    @GetMapping("/usuarios/buscar/{id}")
    ClientDto findById(@PathVariable("id") Long id);
}

