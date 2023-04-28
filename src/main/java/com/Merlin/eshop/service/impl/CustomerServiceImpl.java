package com.Merlin.eshop.service.impl;

import com.Merlin.eshop.dto.RequestDto.CustomerRequestDto;
import com.Merlin.eshop.dto.ResponseDto.CustomerResponseDto;
import com.Merlin.eshop.exception.MobileNumberOrEmailAlreadyRegisteredException;
import com.Merlin.eshop.models.Customer;
import com.Merlin.eshop.repository.CustomerRepository;
import com.Merlin.eshop.service.CustomerService;
import com.Merlin.eshop.transformers.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNumberOrEmailAlreadyRegisteredException {

//        if(customerRepository.findByMobile(customerRequestDto.getMobile())!=null ||
//                customerRepository.findByEmail(customerRequestDto.getEmail())!=null){
//            throw new MobileNumberOrEmailAlreadyRegisteredException("Mobile number/ Email already registered");


        try {
            Customer customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);
            Customer savedCustomer = customerRepository.save(customer);
            return CustomerTransformer.customerToCustomerResponseDto(savedCustomer);
        }
        catch (Exception e){
            throw new MobileNumberOrEmailAlreadyRegisteredException("Mobile number/ Email already registered");
        }

    }
}
