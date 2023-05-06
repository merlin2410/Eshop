package com.Merlin.eshop.controller;

import com.Merlin.eshop.dto.RequestDto.SellerRequestDto;
import com.Merlin.eshop.exception.MobileNumberOrEmailAlreadyRegisteredException;
import com.Merlin.eshop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) throws MobileNumberOrEmailAlreadyRegisteredException {
        //Add seller to the database. If email or mobile already registered, then throw exception
        String message = sellerService.addSeller(sellerRequestDto);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }


}
