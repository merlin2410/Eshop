package com.Merlin.eshop.transformers;

import com.Merlin.eshop.dto.RequestDto.ItemRequestDto;
import com.Merlin.eshop.dto.ResponseDto.ItemResponseDto;
import com.Merlin.eshop.models.Item;

public class ItemTransformer {

    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto){
        Item item = Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
        return item;
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item){
        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                .productName(item.getProduct().getName())
                .unitPrice(item.getProduct().getPrice())
                .requiredQuantity(item.getRequiredQuantity())
                .totalCost(item.getRequiredQuantity()*item.getProduct().getPrice()).build();
        return itemResponseDto;
    }
}
