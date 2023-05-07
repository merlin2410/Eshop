package com.Merlin.eshop.service;

import com.Merlin.eshop.dto.ResponseDto.CartResponseDto;
import com.Merlin.eshop.models.Item;

public interface CartService {

    public CartResponseDto addToCart(Item item, int customerId);
}
