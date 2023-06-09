package com.Merlin.eshop.transformers;

import com.Merlin.eshop.dto.RequestDto.CustomerRequestDto;
import com.Merlin.eshop.dto.ResponseDto.CustomerResponseDto;
import com.Merlin.eshop.models.Customer;

public class CustomerTransformer {

    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .age(customerRequestDto.getAge())
                .name(customerRequestDto.getName())
                .mobile(customerRequestDto.getMobile())
                .email(customerRequestDto.getEmail())
                .address(customerRequestDto.getAddress()).build();
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){

        return CustomerResponseDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobile(customer.getMobile()).build();
    }


}
