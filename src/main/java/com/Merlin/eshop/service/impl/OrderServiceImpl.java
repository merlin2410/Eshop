package com.Merlin.eshop.service.impl;

import com.Merlin.eshop.Enum.OrderStatus;
import com.Merlin.eshop.Enum.ProductStatus;
import com.Merlin.eshop.dto.RequestDto.ItemRequestDto;
import com.Merlin.eshop.dto.RequestDto.OrderRequestDto;
import com.Merlin.eshop.dto.ResponseDto.OrderResponseDto;
import com.Merlin.eshop.exception.CustomerNotFoundException;
import com.Merlin.eshop.exception.InvalidCardException;
import com.Merlin.eshop.exception.ProductNotFoundException;
import com.Merlin.eshop.exception.ProductOutOfStockException;
import com.Merlin.eshop.models.*;
import com.Merlin.eshop.repository.CardRepository;
import com.Merlin.eshop.repository.CustomerRepository;
import com.Merlin.eshop.repository.OrderRepository;
import com.Merlin.eshop.repository.ProductRepository;
import com.Merlin.eshop.service.ItemService;
import com.Merlin.eshop.service.OrderService;
import com.Merlin.eshop.service.ProductService;
import com.Merlin.eshop.transformers.ItemTransformer;
import com.Merlin.eshop.transformers.OrderTransformer;
import jakarta.persistence.criteria.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    ItemService itemService;


    @Override
    public Ordered placeOrder(Cart cart, Card card) throws Exception {
        List<Item> orderedItemList = new ArrayList<>();
        for(Item item: cart.getItems()){
            try {
                productService.decreaseQuantity(item);
                orderedItemList.add(item);
            }
            catch (Exception e){
                throw new ProductOutOfStockException(e.getMessage());
            }
        }

        Ordered order = new Ordered();
        order.setOrderNumber(String.valueOf(UUID.randomUUID()));
        order.setTotalCost(cart.getTotalCost());
        order.setCardUsed(generateMaskedCardNumber(card.getCardNumber()));
        order.setStatus(OrderStatus.CONFIRMED);
        order.setCustomer(cart.getCustomer());
        order.setItems(orderedItemList);

        for(Item item: orderedItemList){
            item.setOrdered(order);

        }

        return order;

    }

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
        Item item;
        Customer customer;
        try {
            item = itemService.addItem(orderRequestDto);
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (CustomerNotFoundException e){
            throw new CustomerNotFoundException(e.getMessage());
        }
        catch (ProductNotFoundException e){
            throw new ProductNotFoundException(e.getMessage());
        }
        catch (ProductOutOfStockException e){
            throw new ProductOutOfStockException(e.getMessage());
        }



        Card card;
        try {
            card = cardRepository.findByCardNumber(orderRequestDto.getCardNumber());
        }
        catch (Exception e){
            throw new InvalidCardException("Card not found");
        }
        if(card==null || !card.getCvv().equals(orderRequestDto.getCvv()) || card.getCustomer()!=customer){
            throw new InvalidCardException("Card/cvv not correct");
        }

        List<Item> orderedItemList = new ArrayList<>();
        try {
            productService.decreaseQuantity(item);
            orderedItemList.add(item);
        }
        catch (Exception e){
            throw new ProductOutOfStockException(e.getMessage());
        }

        Ordered order = new Ordered();
        order.setOrderNumber(String.valueOf(UUID.randomUUID()));
        order.setTotalCost(item.getRequiredQuantity()*item.getProduct().getPrice());
        order.setCustomer(customer);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setCardUsed(generateMaskedCardNumber(card.getCardNumber()));
        order.setItems(orderedItemList);

        customer.getOrderedList().add(order);
        item.setOrdered(order);
        orderRepository.save(order);

        return OrderTransformer.OrderToOrderResponseDto(order);
    }

    public String generateMaskedCardNumber(String cardNumber){
        //Mask all numbers except for last 4 digits
        String maskedCard = "";
        for(int i=0;i<cardNumber.length()-4;i++){
            maskedCard += "X";
        }
        maskedCard += cardNumber.substring(cardNumber.length()-4);
        return maskedCard;
    }


}
