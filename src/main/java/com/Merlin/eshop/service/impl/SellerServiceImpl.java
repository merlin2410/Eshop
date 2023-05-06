package com.Merlin.eshop.service.impl;

import com.Merlin.eshop.dto.RequestDto.SellerRequestDto;
import com.Merlin.eshop.exception.MobileNumberOrEmailAlreadyRegisteredException;
import com.Merlin.eshop.models.Seller;
import com.Merlin.eshop.repository.SellerRepository;
import com.Merlin.eshop.service.SellerService;
import com.Merlin.eshop.transformers.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public String addSeller(SellerRequestDto sellerRequestDto) throws MobileNumberOrEmailAlreadyRegisteredException {
        if(sellerRepository.findByEmail(sellerRequestDto.getEmail())!=null ||
            sellerRepository.findByMobile(sellerRequestDto.getMobile())!=null){
            throw new MobileNumberOrEmailAlreadyRegisteredException("Mobile/Email already registered");
        }
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);
        sellerRepository.save(seller);
        return "Seller added successfully";
    }
}
