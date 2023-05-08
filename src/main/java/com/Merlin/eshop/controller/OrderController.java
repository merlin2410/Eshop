package com.Merlin.eshop.controller;

import com.Merlin.eshop.dto.RequestDto.ItemRequestDto;
import com.Merlin.eshop.dto.RequestDto.OrderRequestDto;
import com.Merlin.eshop.dto.ResponseDto.OrderResponseDto;
import com.Merlin.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto) throws Exception{
        //Place order directly without adding to cart
        OrderResponseDto orderResponseDto = orderService.placeOrder(orderRequestDto);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.ACCEPTED);
    }
}
