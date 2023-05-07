package com.Merlin.eshop.service.impl;

import com.Merlin.eshop.dto.ResponseDto.CartResponseDto;
import com.Merlin.eshop.models.Cart;
import com.Merlin.eshop.models.Customer;
import com.Merlin.eshop.models.Item;
import com.Merlin.eshop.repository.CartRepository;
import com.Merlin.eshop.repository.CustomerRepository;
import com.Merlin.eshop.service.CartService;
import com.Merlin.eshop.transformers.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public CartResponseDto addToCart(Item item, int customerId) {

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();
        cart.setTotalCost(cart.getTotalCost()+item.getProduct().getPrice()*item.getRequiredQuantity());
        cart.setNumberOfItems(cart.getNumberOfItems()+1);
        cart.getItems().add(item);
        item.setCart(cart);

        return CartTransformer.CartToCartResponseDto(cartRepository.save(cart));
    }
}
