package com.Merlin.eshop.service;

import com.Merlin.eshop.dto.RequestDto.ProductRequestDto;
import com.Merlin.eshop.dto.ResponseDto.ProductResponseDto;
import com.Merlin.eshop.exception.SellerNotFoundException;

import java.util.List;

public interface ProductService {

    public String addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException;

    public List<ProductResponseDto> getProductsOfGivenCategory(String category);
}
