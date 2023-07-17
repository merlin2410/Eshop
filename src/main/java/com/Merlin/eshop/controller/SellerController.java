package com.Merlin.eshop.controller;

import com.Merlin.eshop.dto.RequestDto.SellerRequestDto;
import com.Merlin.eshop.dto.RequestDto.SellerUpdateRequestDto;
import com.Merlin.eshop.dto.ResponseDto.SellerResponseDto;
import com.Merlin.eshop.exception.MobileNumberOrEmailAlreadyRegisteredException;
import com.Merlin.eshop.models.Seller;
import com.Merlin.eshop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-by-mail")
    public ResponseEntity getSellerByEmail(@RequestParam("email") String email) throws Exception{
        //Get seller for given email or throw an exception
        SellerResponseDto sellerResponseDto = sellerService.getSellerByEmail(email);
        return new ResponseEntity<>(sellerResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-by-mobile")
    public ResponseEntity getSellerByMobile(@RequestParam("mobile") String mobile) throws Exception{
        //Get seller by mobile or throw exception
        SellerResponseDto sellerResponseDto = sellerService.getSellerByMobile(mobile);
        return new ResponseEntity<>(sellerResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-by-id")
    public ResponseEntity getSellerById(@RequestParam("id") int id) throws Exception{
        //Get seller by id
        SellerResponseDto sellerResponseDto = sellerService.getSellerById(id);
        return new ResponseEntity<>(sellerResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/view-all")
    public ResponseEntity getAllSellers() {
        //Get all sellers.
        List<SellerResponseDto> sellerResponseDtoList = sellerService.getAllSellers();
        return new ResponseEntity<>(sellerResponseDtoList,HttpStatus.ACCEPTED);
    }

    @PutMapping("/update-by-mail")
    public ResponseEntity updateSellerByEmail(@RequestBody SellerUpdateRequestDto sellerUpdateRequestDto) throws Exception{
        //Update seller by email
        SellerResponseDto sellerResponseDto = sellerService.updateSellerByEmail(sellerUpdateRequestDto);
        return new ResponseEntity<>(sellerResponseDto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-by-id")
    public ResponseEntity deleteSellerById(@RequestParam("id") int id) throws Exception{
        //Delete seller by id
        String message = sellerService.deleteSellerById(id);
        return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
    }


}
