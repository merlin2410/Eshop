package com.Merlin.eshop.service;

import com.Merlin.eshop.dto.RequestDto.SellerRequestDto;
import com.Merlin.eshop.dto.RequestDto.SellerUpdateRequestDto;
import com.Merlin.eshop.dto.ResponseDto.SellerResponseDto;
import com.Merlin.eshop.exception.MobileNumberOrEmailAlreadyRegisteredException;

import java.util.List;

public interface SellerService {

    public String addSeller(SellerRequestDto sellerRequestDto) throws MobileNumberOrEmailAlreadyRegisteredException;

    public SellerResponseDto getSellerByEmail(String email) throws Exception;

    public SellerResponseDto getSellerByMobile(String mobile) throws Exception;

    public SellerResponseDto getSellerById(int id) throws Exception;

    public List<SellerResponseDto> getAllSellers();

    public SellerResponseDto updateSellerByEmail(SellerUpdateRequestDto sellerUpdateRequestDto) throws Exception;

    public String deleteSellerById(int id) throws Exception;
}
