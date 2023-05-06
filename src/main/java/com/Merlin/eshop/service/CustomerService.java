package com.Merlin.eshop.service;

import com.Merlin.eshop.dto.RequestDto.CustomerMobileOrEmailRequestDto;
import com.Merlin.eshop.dto.RequestDto.CustomerRequestDto;
import com.Merlin.eshop.dto.ResponseDto.CustomerResponseDto;
import com.Merlin.eshop.exception.CustomerNotFoundException;
import com.Merlin.eshop.exception.MobileNumberOrEmailAlreadyRegisteredException;

import java.util.List;

public interface CustomerService {

    public String addCustomer(CustomerRequestDto customerRequestDto) throws MobileNumberOrEmailAlreadyRegisteredException;

    public List<CustomerResponseDto> viewAllCustomers() throws CustomerNotFoundException;

    public CustomerResponseDto getCustomerByMailOrMobile(CustomerMobileOrEmailRequestDto customerMobileOrEmailRequestDto) throws CustomerNotFoundException;

    public List<CustomerResponseDto> getCustomersGreaterThanAge(int age);

    public List<CustomerResponseDto> getCustomersLessThanAge(int age);
}
