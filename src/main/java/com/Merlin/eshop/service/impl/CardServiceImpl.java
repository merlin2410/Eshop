package com.Merlin.eshop.service.impl;

import com.Merlin.eshop.dto.RequestDto.CardRequestDto;
import com.Merlin.eshop.exception.CardAlreadyAddedException;
import com.Merlin.eshop.exception.CustomerNotFoundException;
import com.Merlin.eshop.models.Card;
import com.Merlin.eshop.models.Customer;
import com.Merlin.eshop.repository.CardRepository;
import com.Merlin.eshop.repository.CustomerRepository;
import com.Merlin.eshop.service.CardService;
import com.Merlin.eshop.transformers.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public String addCard(CardRequestDto cardRequestDto) throws Exception {
        if(cardRepository.findByCardNumber(cardRequestDto.getCardNumber())!=null){
            throw new CardAlreadyAddedException("Card is already added");
        }
        Customer customer = customerRepository.findByMobile(cardRequestDto.getMobile());
        if(customer==null){
            throw new CustomerNotFoundException("Customer is not found");
        }
        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCardList().add(card);
        customerRepository.save(customer);

        return "Card Successfully Added";
    }
}
