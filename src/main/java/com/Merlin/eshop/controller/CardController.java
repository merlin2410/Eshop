package com.Merlin.eshop.controller;

import com.Merlin.eshop.dto.RequestDto.CardRequestDto;
import com.Merlin.eshop.exception.CustomerNotFoundException;
import com.Merlin.eshop.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) throws Exception {
        //Add card details of a customer. If customer not found it throws an exception
        //If card is already added, an exception is thrown
        String message = cardService.addCard(cardRequestDto);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }
}
