package com.cart.shopping.cart.core.shared.useCase;

public interface IShoppingCart <E,S>{
    S executar(E entrada);
}
