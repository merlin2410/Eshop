package com.Merlin.eshop.service.impl;

import com.Merlin.eshop.dto.RequestDto.CustomerMobileOrEmailRequestDto;
import com.Merlin.eshop.dto.RequestDto.CustomerRequestDto;
import com.Merlin.eshop.dto.ResponseDto.CustomerResponseDto;
import com.Merlin.eshop.exception.CardNotFoundException;
import com.Merlin.eshop.exception.CustomerNotFoundException;
import com.Merlin.eshop.exception.MobileNumberOrEmailAlreadyRegisteredException;
import com.Merlin.eshop.models.Card;
import com.Merlin.eshop.models.Cart;
import com.Merlin.eshop.models.Customer;
import com.Merlin.eshop.repository.CardRepository;
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

    @Autowired
    CardRepository cardRepository;

    @Override
    public String addCustomer(CustomerRequestDto customerRequestDto) throws MobileNumberOrEmailAlreadyRegisteredException {

//        if(customerRepository.findByMobile(customerRequestDto.getMobile())!=null ||
//                customerRepository.findByEmail(customerRequestDto.getEmail())!=null){
//            throw new MobileNumberOrEmailAlreadyRegisteredException("Mobile number/ Email already registered");


        try {
            Customer customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);
            Cart cart = new Cart();
            cart.setCustomer(customer);
            customer.setCart(cart);
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
        if(customer==null){
            throw new CustomerNotFoundException("Customer not found");
        }
        return CustomerTransformer.customerToCustomerResponseDto(customer);
    }

    @Override
    public List<CustomerResponseDto> getCustomersGreaterThanAge(int age) {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();
        for(Customer customer: customerList){
            if(customer.getAge()>age){
                customerResponseDtoList.add(CustomerTransformer.customerToCustomerResponseDto(customer));
            }
        }
        return customerResponseDtoList;
    }

    @Override
    public List<CustomerResponseDto> getCustomersLessThanAge(int age) {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();
        for(Customer customer: customerList){
            if(customer.getAge()<age){
                customerResponseDtoList.add(CustomerTransformer.customerToCustomerResponseDto(customer));
            }
        }
        return customerResponseDtoList;
    }

    @Override
    public List<CustomerResponseDto> getCustomerByCardType(String cardType) throws Exception {
        cardType = cardType.toUpperCase();
        List<Card> cardList = cardRepository.findAll();
        if(cardList.isEmpty()){
            throw new CardNotFoundException("Card not found");
        }
        List<Card> cardOfGiveTypeList = new ArrayList<>();
        for(Card card: cardList){
            if(card.getCardType().toString().equals(cardType)){
                cardOfGiveTypeList.add(card);
            }
        }
        if(cardOfGiveTypeList.isEmpty()){
            throw new CardNotFoundException("Card of given type not present");
        }
        List<Customer> customerList = new ArrayList<>();
        for(Card card: cardOfGiveTypeList){
            if(!customerList.contains(card.getCustomer())){
                customerList.add(card.getCustomer());
            }
        }
        if(customerList.isEmpty()){
            throw new CustomerNotFoundException("Customer with given card type not present");
        }
        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();
        for(Customer customer: customerList){
            customerResponseDtoList.add(CustomerTransformer.customerToCustomerResponseDto(customer));
        }

        return customerResponseDtoList;
    }

    @Override
    public CustomerResponseDto updateCustomer(CustomerRequestDto customerRequestDto) throws Exception {
        Customer customer = getCustomerFromByMobileOrMail(customerRequestDto);
        if(customer==null){
            throw new CustomerNotFoundException("Customer Not Found");
        }
        if(customerRequestDto.getName()!=null && customerRequestDto.getName().length()!=0){
            customer.setName(customerRequestDto.getName());
        }
        if(customerRequestDto.getAge()!=0){
            customer.setAge(customerRequestDto.getAge());
        }
        if(customerRequestDto.getAddress()!=null && customerRequestDto.getAddress().length()!=0){
            customer.setAddress(customerRequestDto.getAddress());
        }
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerTransformer.customerToCustomerResponseDto(customer);
    }

    @Override
    public String deleteCustomer(CustomerMobileOrEmailRequestDto customerMobileOrEmailRequestDto) {
        if(customerMobileOrEmailRequestDto.getMobile()!=null && customerMobileOrEmailRequestDto.getMobile().length()!=0){
            try {
                Customer customer = customerRepository.findByMobile(customerMobileOrEmailRequestDto.getMobile());
                customerRepository.delete(customer);
                return "Customer deleted successfully";
            }
            catch (Exception e){

            }

        } else if (customerMobileOrEmailRequestDto.getEmail() != null && customerMobileOrEmailRequestDto.getEmail().length() != 0) {
            try {
                Customer customer = customerRepository.findByEmail(customerMobileOrEmailRequestDto.getEmail());
                customerRepository.delete(customer);
                return "Customer deleted Successfully";
            }
            catch (Exception e){

            }

        }
        return "Mobile number/Email invalid";
    }

    public Customer getCustomerFromByMobileOrMail(CustomerRequestDto customerRequestDto) throws Exception{
        if(customerRequestDto.getMobile()==null && customerRequestDto.getEmail()==null){
            throw new Exception("Mobile Number & Email not entered");
        }
        if(customerRequestDto.getMobile()!=null && customerRequestDto.getMobile().length()==0 &&
                customerRequestDto.getEmail()!=null && customerRequestDto.getEmail().length()==0){
            throw new Exception("Mobile or Email not entered");
        }
        Customer customer = null;
        if(customerRequestDto.getEmail()==null || customerRequestDto.getEmail().length()==0){
            if(customerRequestDto.getMobile()!=null){
                try {
                    customer = customerRepository.findByMobile(customerRequestDto.getMobile());
                }
                catch (Exception e){
                    throw new CustomerNotFoundException("Customer Not Found");
                }
            }
        } else if (customerRequestDto.getMobile() == null || customerRequestDto.getMobile().length() == 0) {
            if(customerRequestDto.getEmail()!=null){
                try {
                    customer = customerRepository.findByEmail(customerRequestDto.getEmail());
                }
                catch (Exception e){
                    throw new CustomerNotFoundException("Customer Not Found");
                }
            }
        } else if (customerRequestDto.getEmail() != null && customerRequestDto.getEmail().length() != 0 &&
                customerRequestDto.getMobile()!=null && customerRequestDto.getMobile().length()!=0) {
            try {
                customer = customerRepository.findByMobile(customerRequestDto.getMobile());
            }
            catch (Exception e){
                throw new CustomerNotFoundException("Customer Not Found");
            }
        }
        return customer;
    }
}
