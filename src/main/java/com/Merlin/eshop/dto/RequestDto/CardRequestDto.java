package com.Merlin.eshop.dto.RequestDto;

import com.Merlin.eshop.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {

    String mobile;

    String cardNumber;

    String cvv;

    Date expiryDate;

    CardType cardType;

}
