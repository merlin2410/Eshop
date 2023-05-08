package com.Merlin.eshop.service;

import com.Merlin.eshop.dto.RequestDto.CheckoutCartRequestDto;
import com.Merlin.eshop.dto.ResponseDto.CartResponseDto;
import com.Merlin.eshop.dto.ResponseDto.OrderResponseDto;
import com.Merlin.eshop.models.Item;

public interface CartService {

    public CartResponseDto addToCart(Item item, int customerId);

    public OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception;
}
