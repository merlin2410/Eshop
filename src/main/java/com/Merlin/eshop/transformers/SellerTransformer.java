package com.Merlin.eshop.transformers;

import com.Merlin.eshop.dto.RequestDto.SellerRequestDto;
import com.Merlin.eshop.models.Seller;

public class SellerTransformer {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        Seller seller = Seller.builder()
                .name(sellerRequestDto.getName())
                .age(sellerRequestDto.getAge())
                .email(sellerRequestDto.getEmail())
                .mobile(sellerRequestDto.getMobile())
                .enterprise(sellerRequestDto.getEnterprise()).build();
        return seller;
    }
}
