package com.Merlin.eshop.controller;

import com.Merlin.eshop.dto.RequestDto.ProductRequestDto;
import com.Merlin.eshop.dto.ResponseDto.ProductResponseDto;
import com.Merlin.eshop.exception.SellerNotFoundException;
import com.Merlin.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto) throws SellerNotFoundException {
        //Add product details into the database
        String message = productService.addProduct(productRequestDto);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{category}")
    public ResponseEntity getProductsOfGivenCategory(@PathVariable("category") String category){
        //Return a list of products coming under a particular category
        List<ProductResponseDto> productResponseDtoList = productService.getProductsOfGivenCategory(category);
        return new ResponseEntity<>(productResponseDtoList,HttpStatus.ACCEPTED);
    }

    @GetMapping("/category-price-less-than")
    public ResponseEntity getProductsOfGivenCategoryLessThanPrice(@RequestParam("category") String category, @RequestParam("price") int price){
        List<ProductResponseDto> productResponseDtoList = productService.getProductsOfGivenCategoryLessThanPrice(category,price);
        return new ResponseEntity<>(productResponseDtoList,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-all-by-seller-mail")
    public ResponseEntity getAllProductsBySeller(@RequestParam("sellerEmail") String sellerEmail) throws Exception{
        List<ProductResponseDto> productResponseDtoList = productService.getAllProductsBySeller(sellerEmail);
        return new ResponseEntity<>(productResponseDtoList,HttpStatus.ACCEPTED);
    }

}
