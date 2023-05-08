package com.Merlin.eshop.service.impl;

import com.Merlin.eshop.Enum.OrderStatus;
import com.Merlin.eshop.Enum.ProductStatus;
import com.Merlin.eshop.exception.ProductNotFoundException;
import com.Merlin.eshop.exception.ProductOutOfStockException;
import com.Merlin.eshop.models.Card;
import com.Merlin.eshop.models.Cart;
import com.Merlin.eshop.models.Item;
import com.Merlin.eshop.models.Ordered;
import com.Merlin.eshop.repository.OrderRepository;
import com.Merlin.eshop.repository.ProductRepository;
import com.Merlin.eshop.service.OrderService;
import com.Merlin.eshop.service.ProductService;
import jakarta.persistence.criteria.Order;
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

        return order;

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
