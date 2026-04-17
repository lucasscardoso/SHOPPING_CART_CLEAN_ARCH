package com.cart.shopping.cart.core.interfaces.feign;

import com.cart.shopping.cart.core.shared.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface IProductFeignClient {

    @GetMapping("/busca/{id}")
    ProductDto findById(@PathVariable Long id);
}
