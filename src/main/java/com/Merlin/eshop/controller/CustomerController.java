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
                ((customerMobileOrEmailRequestDto.getMobile()!=null && customerMobileOrEmailRequestDto.getMobile().length()==0) &&
                        (customerMobileOrEmailRequestDto.getEmail()!=null && customerMobileOrEmailRequestDto.getEmail().length()==0))){
            return new ResponseEntity<>("Mobile/Email Not Entered",HttpStatus.BAD_REQUEST);
        }
        CustomerResponseDto customerResponseDto = customerService.getCustomerByMailOrMobile(customerMobileOrEmailRequestDto);
        return new ResponseEntity<>(customerResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-greater-than-age")
    public ResponseEntity getCustomersGreaterThanAge(@RequestParam("age") int age){
        //Get list of all customers greater than the given age
        List<CustomerResponseDto> customerResponseDtoList = customerService.getCustomersGreaterThanAge(age);
        return new ResponseEntity<>(customerResponseDtoList,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-less-than-age")
    public ResponseEntity getCustomersLessThanAge(@RequestParam("age") int age){
        //Get list of all customers less than the given age
        List<CustomerResponseDto> customerResponseDtoList = customerService.getCustomersLessThanAge(age);
        return new ResponseEntity<>(customerResponseDtoList,HttpStatus.ACCEPTED);
    }

    @GetMapping("/card/{cardType}")
    public ResponseEntity getCustomersByCardType(@PathVariable("cardType") String cardType) throws Exception{
        //Get list of all customers with a given card type
        List<CustomerResponseDto> customerResponseDtoList = customerService.getCustomerByCardType(cardType);
        return new ResponseEntity<>(customerResponseDtoList,HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity updateCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws Exception{
        //Update existing customer. If customer does not exist throw an exception
        CustomerResponseDto customerResponseDto = customerService.updateCustomer(customerRequestDto);
        return new ResponseEntity<>(customerResponseDto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@RequestBody CustomerMobileOrEmailRequestDto customerMobileOrEmailRequestDto){
        //Delete a customer. If customer doesn't exist do nothing. No need for exceptions
        String message = customerService.deleteCustomer(customerMobileOrEmailRequestDto);
        return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
    }
}
