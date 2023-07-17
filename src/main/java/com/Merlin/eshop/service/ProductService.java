package com.Merlin.eshop.service;

import com.Merlin.eshop.dto.RequestDto.ProductRequestDto;
import com.Merlin.eshop.dto.ResponseDto.ProductResponseDto;
import com.Merlin.eshop.exception.SellerNotFoundException;
import com.Merlin.eshop.models.Item;

import java.util.List;

public interface ProductService {

    public String addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException;

    public List<ProductResponseDto> getProductsOfGivenCategory(String category);

    public void decreaseQuantity(Item item) throws Exception;

    public List<ProductResponseDto> getProductsOfGivenCategoryLessThanPrice(String category, int price);

    public List<ProductResponseDto> getAllProductsBySeller(String sellerEmail) throws Exception;
}
