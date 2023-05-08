package com.Merlin.eshop.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDto {

    int customerId;

    int productId;

    int requiredQuantity;

    String cardNumber;

    String cvv;

}
