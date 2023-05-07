package com.Merlin.eshop.transformers;

import com.Merlin.eshop.dto.RequestDto.CardRequestDto;
import com.Merlin.eshop.models.Card;

public class CardTransformer {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){
        Card card = Card.builder()
                .cardNumber(cardRequestDto.getCardNumber())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .expiryDate(cardRequestDto.getExpiryDate()).build();
        return card;
    }
}
