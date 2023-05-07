package com.Merlin.eshop.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponseDto {

    String customerName;

    int numberOfItems;

    List<ItemResponseDto> itemResponseDtoList;

    int totalCost;
}
