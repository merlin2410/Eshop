package com.Merlin.eshop.transformers;

import com.Merlin.eshop.dto.ResponseDto.CartResponseDto;
import com.Merlin.eshop.dto.ResponseDto.ItemResponseDto;
import com.Merlin.eshop.models.Cart;
import com.Merlin.eshop.models.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponseDto CartToCartResponseDto(Cart cart){
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item: cart.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }
        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .customerName(cart.getCustomer().getName())
                .numberOfItems(cart.getNumberOfItems())
                .itemResponseDtoList(itemResponseDtoList)
                .totalCost(cart.getTotalCost()).build();
        return cartResponseDto;
    }
}
