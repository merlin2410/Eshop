package com.Merlin.eshop.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckoutCartRequestDto {

    int customerId;

    String cardNumber;

    String cvv;
}
