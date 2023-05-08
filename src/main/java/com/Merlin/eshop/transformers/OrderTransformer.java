package com.Merlin.eshop.transformers;

import com.Merlin.eshop.dto.ResponseDto.ItemResponseDto;
import com.Merlin.eshop.dto.ResponseDto.OrderResponseDto;
import com.Merlin.eshop.models.Item;
import com.Merlin.eshop.models.Ordered;

import java.util.ArrayList;
import java.util.List;

public class OrderTransformer {

    public static OrderResponseDto OrderToOrderResponseDto(Ordered order){
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item: order.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .orderNumber(order.getOrderNumber())
                .orderDate(order.getDate())
                .customerName(order.getCustomer().getName())
                .totalCost(order.getTotalCost())
                .cardUsed(order.getCardUsed())
                .orderStatus(order.getStatus())
                .itemResponseDtoList(itemResponseDtoList)
                .numberOfItems(order.getItems().size()).build();
        return orderResponseDto;
    }
}
