package com.Merlin.eshop.transformers;

import com.Merlin.eshop.dto.RequestDto.ProductRequestDto;
import com.Merlin.eshop.dto.ResponseDto.ProductResponseDto;
import com.Merlin.eshop.models.Product;

public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .availableQuantity(productRequestDto.getAvailableQuantity())
                .price(productRequestDto.getPrice())
                .category(productRequestDto.getCategory()).build();
        return product;
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){
        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .productCategory(product.getCategory())
                .productStatus(product.getProductStatus()).build();
        return productResponseDto;
    }
}
