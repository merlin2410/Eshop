package com.Merlin.eshop.service.impl;

import com.Merlin.eshop.dto.RequestDto.SellerRequestDto;
import com.Merlin.eshop.dto.RequestDto.SellerUpdateRequestDto;
import com.Merlin.eshop.dto.ResponseDto.SellerResponseDto;
import com.Merlin.eshop.exception.MobileNumberOrEmailAlreadyRegisteredException;
import com.Merlin.eshop.exception.SellerNotFoundException;
import com.Merlin.eshop.models.Seller;
import com.Merlin.eshop.repository.SellerRepository;
import com.Merlin.eshop.service.SellerService;
import com.Merlin.eshop.transformers.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public SellerResponseDto getSellerByEmail(String email) throws Exception {
        try {
            Seller seller = sellerRepository.findByEmail(email);
            return SellerTransformer.SellerToSellerResponseDto(seller);
        }
        catch (Exception e){
            throw new SellerNotFoundException("Seller Not Found");
        }
    }

    @Override
    public SellerResponseDto getSellerByMobile(String mobile) throws Exception {
        try {
            Seller seller = sellerRepository.findByMobile(mobile);
            return SellerTransformer.SellerToSellerResponseDto(seller);
        }
        catch (Exception e){
            throw new SellerNotFoundException("Seller Not Found");
        }
    }

    @Override
    public SellerResponseDto getSellerById(int id) throws Exception {
        try {
            Seller seller = sellerRepository.findById(id).get();
            return SellerTransformer.SellerToSellerResponseDto(seller);
        }
        catch (Exception e){
            throw new SellerNotFoundException("Seller Not Found");
        }
    }

    @Override
    public List<SellerResponseDto> getAllSellers() {
        List<Seller> sellerList = sellerRepository.findAll();
        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for(Seller seller: sellerList){
            sellerResponseDtoList.add(SellerTransformer.SellerToSellerResponseDto(seller));
        }
        return sellerResponseDtoList;
    }

    @Override
    public SellerResponseDto updateSellerByEmail(SellerUpdateRequestDto sellerUpdateRequestDto) throws Exception {
        if(sellerUpdateRequestDto.getEmail()==null || (sellerUpdateRequestDto.getEmail()!=null && sellerUpdateRequestDto.getEmail().length()==0)){
            throw new Exception("Email not entered!");
        }
        try {
            Seller seller = sellerRepository.findByEmail(sellerUpdateRequestDto.getEmail());
            if (sellerUpdateRequestDto.getName()!=null && sellerUpdateRequestDto.getName().length()!=0){
                seller.setName(sellerUpdateRequestDto.getName());
            }
            if (sellerUpdateRequestDto.getAge()!=0 ){
                seller.setAge(sellerUpdateRequestDto.getAge());
            }
            if (sellerUpdateRequestDto.getEnterprise()!=null && sellerUpdateRequestDto.getEnterprise().length()!=0){
                seller.setEnterprise(sellerUpdateRequestDto.getEnterprise());
            }
            Seller savedSeller = sellerRepository.save(seller);
            return SellerTransformer.SellerToSellerResponseDto(savedSeller);

        }
        catch (Exception e){
            throw new SellerNotFoundException("Seller not found/ Invalid email");
        }
    }

    @Override
    public String deleteSellerById(int id) throws Exception {
        try {
            sellerRepository.deleteById(id);
        }
        catch (Exception e){
            throw new SellerNotFoundException("Seller is not found");
        }
        return "Seller deleted successfully";
    }
}
