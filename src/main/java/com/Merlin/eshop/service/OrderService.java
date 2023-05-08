package com.Merlin.eshop.service;

import com.Merlin.eshop.models.Card;
import com.Merlin.eshop.models.Cart;
import com.Merlin.eshop.models.Ordered;


public interface OrderService {

    public Ordered placeOrder(Cart cart, Card card) throws Exception;
}
