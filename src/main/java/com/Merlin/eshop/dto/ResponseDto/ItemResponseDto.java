package com.Merlin.eshop.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemResponseDto {

    String productName;

    int unitPrice;

    int requiredQuantity;

    int totalCost;
}
