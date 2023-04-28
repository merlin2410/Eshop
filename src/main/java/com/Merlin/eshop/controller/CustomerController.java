package com.Merlin.eshop.controller;

import com.Merlin.eshop.dto.RequestDto.CustomerMobileOrEmailRequestDto;
import com.Merlin.eshop.dto.RequestDto.CustomerRequestDto;
import com.Merlin.eshop.dto.ResponseDto.CustomerResponseDto;
import com.Merlin.eshop.exception.CustomerNotFoundException;
import com.Merlin.eshop.exception.MobileNumberOrEmailAlreadyRegisteredException;
import com.Merlin.eshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobileNumberOrEmailAlreadyRegisteredException {
        //Add customer. If mobile number or email already registered, throw an exception
        String message = customerService.addCustomer(customerRequestDto);
        return new ResponseEntity(message,HttpStatus.ACCEPTED);
    }

    @GetMapping("/view-all")
    public ResponseEntity viewAllCustomers() throws CustomerNotFoundException {
        //Returns a list of customers with name, email and mobile number
        List<CustomerResponseDto> customerResponseDtoList = customerService.viewAllCustomers();
        return new ResponseEntity(customerResponseDtoList,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get")
    public ResponseEntity getCustomersByMailOrMobile(@RequestBody CustomerMobileOrEmailRequestDto customerMobileOrEmailRequestDto) throws CustomerNotFoundException{
        //Returns customer using email id or mobile number. If customer not present, it throws an exception
        if((customerMobileOrEmailRequestDto.getMobile()==null &&
           customerMobileOrEmailRequestDto.getEmail()==null) ||
                (customerMobileOrEmailRequestDto.getMobile().length()==0 &&
                        customerMobileOrEmailRequestDto.getEmail().length()==0)){
            return new ResponseEntity<>("Mobile/Email Not Entered",HttpStatus.BAD_REQUEST);
        }
        CustomerResponseDto customerResponseDto = customerService.getCustomerByMailOrMobile(customerMobileOrEmailRequestDto);
        return new ResponseEntity<>(customerResponseDto,HttpStatus.ACCEPTED);
    }
}
