package com.Merlin.eshop.service.impl;

import com.Merlin.eshop.dto.RequestDto.CustomerMobileOrEmailRequestDto;
import com.Merlin.eshop.dto.RequestDto.CustomerRequestDto;
import com.Merlin.eshop.dto.ResponseDto.CustomerResponseDto;
import com.Merlin.eshop.exception.CustomerNotFoundException;
import com.Merlin.eshop.exception.MobileNumberOrEmailAlreadyRegisteredException;
import com.Merlin.eshop.models.Customer;
import com.Merlin.eshop.repository.CustomerRepository;
import com.Merlin.eshop.service.CustomerService;
import com.Merlin.eshop.transformers.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public String addCustomer(CustomerRequestDto customerRequestDto) throws MobileNumberOrEmailAlreadyRegisteredException {

//        if(customerRepository.findByMobile(customerRequestDto.getMobile())!=null ||
//                customerRepository.findByEmail(customerRequestDto.getEmail())!=null){
//            throw new MobileNumberOrEmailAlreadyRegisteredException("Mobile number/ Email already registered");


        try {
            Customer customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);
            Customer savedCustomer = customerRepository.save(customer);
            return savedCustomer.getName()+" has been successfully added";
        }
        catch (Exception e){
            throw new MobileNumberOrEmailAlreadyRegisteredException("Mobile number/ Email already registered");
        }

    }

    @Override
    public List<CustomerResponseDto> viewAllCustomers() throws CustomerNotFoundException {
        List<Customer> customerList = customerRepository.findAll();
        if(customerList.isEmpty()){
            throw new CustomerNotFoundException("No Customers Registered");
        }
        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();
        for(Customer customer: customerList){
            customerResponseDtoList.add(CustomerTransformer.customerToCustomerResponseDto(customer));
        }
        return customerResponseDtoList;
    }

    @Override
    public CustomerResponseDto getCustomerByMailOrMobile(CustomerMobileOrEmailRequestDto customerMobileOrEmailRequestDto) throws CustomerNotFoundException {
        Customer customer = null;
        if(customerMobileOrEmailRequestDto.getEmail()!=null && customerMobileOrEmailRequestDto.getEmail().length()!=0){
            try {
                customer = customerRepository.findByEmail(customerMobileOrEmailRequestDto.getEmail());
            }
            catch (Exception e){
                throw new CustomerNotFoundException("Customer not found");
            }

        }
        else if(customerMobileOrEmailRequestDto.getMobile()!=null && customerMobileOrEmailRequestDto.getMobile().length()!=0) {
            try {
                customer = customerRepository.findByMobile(customerMobileOrEmailRequestDto.getMobile());
            }
            catch (Exception e){
                throw new CustomerNotFoundException("Customer not found");
            }
        }
        return CustomerTransformer.customerToCustomerResponseDto(customer);
    }
}
