package com.Merlin.eshop.service;

import com.Merlin.eshop.dto.RequestDto.SellerRequestDto;
import com.Merlin.eshop.exception.MobileNumberOrEmailAlreadyRegisteredException;

public interface SellerService {

    public String addSeller(SellerRequestDto sellerRequestDto) throws MobileNumberOrEmailAlreadyRegisteredException;
}
