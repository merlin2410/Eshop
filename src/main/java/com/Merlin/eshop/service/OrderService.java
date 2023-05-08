package com.Merlin.eshop.service;

import com.Merlin.eshop.dto.RequestDto.ItemRequestDto;
import com.Merlin.eshop.dto.RequestDto.OrderRequestDto;
import com.Merlin.eshop.dto.ResponseDto.OrderResponseDto;
import com.Merlin.eshop.models.Card;
import com.Merlin.eshop.models.Cart;
import com.Merlin.eshop.models.Ordered;


public interface OrderService {

    public Ordered placeOrder(Cart cart, Card card) throws Exception;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception;
}
