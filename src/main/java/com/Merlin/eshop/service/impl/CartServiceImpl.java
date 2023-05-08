package com.Merlin.eshop.service.impl;

import com.Merlin.eshop.dto.RequestDto.CheckoutCartRequestDto;
import com.Merlin.eshop.dto.ResponseDto.CartResponseDto;
import com.Merlin.eshop.dto.ResponseDto.OrderResponseDto;
import com.Merlin.eshop.exception.CustomerNotFoundException;
import com.Merlin.eshop.exception.EmptyCartException;
import com.Merlin.eshop.exception.InvalidCardException;
import com.Merlin.eshop.exception.ProductOutOfStockException;
import com.Merlin.eshop.models.*;
import com.Merlin.eshop.repository.*;
import com.Merlin.eshop.service.CartService;
import com.Merlin.eshop.service.OrderService;
import com.Merlin.eshop.transformers.CartTransformer;
import com.Merlin.eshop.transformers.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;
    @Override
    public CartResponseDto addToCart(Item item, int customerId) {

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();
        cart.setTotalCost(cart.getTotalCost()+item.getProduct().getPrice()*item.getRequiredQuantity());
        cart.setNumberOfItems(cart.getNumberOfItems()+1);
        cart.getItems().add(item);
        item.setCart(cart);

        Cart savedCart = cartRepository.save(cart);
        return CartTransformer.CartToCartResponseDto(savedCart);
    }

    @Override
    public OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {
        Customer customer;
        try {
            customer = customerRepository.findById(checkoutCartRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Customer not found/ Invalid customer Id");
        }
        Card card;
        try {
            card = cardRepository.findByCardNumber(checkoutCartRequestDto.getCardNumber());
        }
        catch (Exception e){
            throw new InvalidCardException("Card not found");
        }
        if(card==null || !card.getCvv().equals(checkoutCartRequestDto.getCvv()) || card.getCustomer()!=customer){
            throw new InvalidCardException("Card/cvv not correct");
        }
        Cart cart = customer.getCart();
        if(cart.getNumberOfItems()==0){
            throw new EmptyCartException("Cart is Empty! Add items!");
        }
        try {
            Ordered order = orderService.placeOrder(cart, card);
            customer.getOrderedList().add(order);
            resetCart(cart);
            orderRepository.save(order);
            return OrderTransformer.OrderToOrderResponseDto(order);
        }
        catch (Exception e){
            throw new ProductOutOfStockException(e.getMessage());
        }

    }

    public void resetCart(Cart cart){
        cart.setNumberOfItems(0);
        cart.setTotalCost(0);
        for(Item item: cart.getItems()){
            item.setCart(null);
        }
        cart.getItems().clear();
    }
}
