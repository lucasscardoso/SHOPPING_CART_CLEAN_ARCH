package com.cart.shopping.cart.core.shared.useCase;

import com.cart.shopping.cart.core.entity.Cart;
import com.cart.shopping.cart.core.shared.dto.SaveCartRequest;
import com.cart.shopping.cart.core.shared.dto.ShoppingCartDto;
import com.cart.shopping.cart.core.shared.dto.ShoppingCartRequest;

public interface ISaveShoppingCart extends IShoppingCart<SaveCartRequest, ShoppingCartDto>{

}
