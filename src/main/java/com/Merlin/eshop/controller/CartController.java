package com.Merlin.eshop.controller;

import com.Merlin.eshop.dto.RequestDto.CheckoutCartRequestDto;
import com.Merlin.eshop.dto.RequestDto.ItemRequestDto;
import com.Merlin.eshop.dto.ResponseDto.CartResponseDto;
import com.Merlin.eshop.dto.ResponseDto.OrderResponseDto;
import com.Merlin.eshop.models.Item;
import com.Merlin.eshop.service.CartService;
import com.Merlin.eshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto) throws Exception{
        //Add items to the cart. Throws exception if product or customer not valid
        Item item = itemService.addItem(itemRequestDto);
        CartResponseDto cartResponseDto = cartService.addToCart(item, itemRequestDto.getCustomerId());
        return new ResponseEntity<>(cartResponseDto, HttpStatus.ACCEPTED);
    }

    @PostMapping("/checkout")
    public ResponseEntity checkoutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto) throws Exception{
        //Checkout from cart. Throws exceptions for invalid customer, out of stock products, empty cart, invalid card details
        OrderResponseDto orderResponseDto = cartService.checkoutCart(checkoutCartRequestDto);
        return new ResponseEntity<>(orderResponseDto,HttpStatus.ACCEPTED);
    }
}
