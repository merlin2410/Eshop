package com.Merlin.eshop.service;

import com.Merlin.eshop.dto.RequestDto.CardRequestDto;

public interface CardService {

    public String addCard(CardRequestDto cardRequestDto) throws Exception;
}
