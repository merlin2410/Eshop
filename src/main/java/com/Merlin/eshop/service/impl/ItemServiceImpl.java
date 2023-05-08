package com.Merlin.eshop.service.impl;

import com.Merlin.eshop.Enum.ProductStatus;
import com.Merlin.eshop.dto.RequestDto.ItemRequestDto;
import com.Merlin.eshop.dto.RequestDto.OrderRequestDto;
import com.Merlin.eshop.exception.CustomerNotFoundException;
import com.Merlin.eshop.exception.ProductNotFoundException;
import com.Merlin.eshop.exception.ProductOutOfStockException;
import com.Merlin.eshop.models.Customer;
import com.Merlin.eshop.models.Item;
import com.Merlin.eshop.models.Product;
import com.Merlin.eshop.repository.CustomerRepository;
import com.Merlin.eshop.repository.ItemRepository;
import com.Merlin.eshop.repository.ProductRepository;
import com.Merlin.eshop.service.ItemService;
import com.Merlin.eshop.transformers.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {
        Customer customer;
        try {
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Customer Not Found/ Invalid customer Id");
        }
        Product product;
        try {
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Product Not Found/ Invalid product Id");
        }
        if(product.getAvailableQuantity()<itemRequestDto.getRequiredQuantity() || product.getProductStatus()!= ProductStatus.AVAILABLE){
            throw new ProductOutOfStockException("Product is out of stock");
        }
        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto);
        item.setProduct(product);
        product.getItemList().add(item);
        return itemRepository.save(item);
    }

    @Override
    public Item addItem(OrderRequestDto orderRequestDto) throws Exception {
        Customer customer;
        try {
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Customer Not Found/ Invalid customer Id");
        }
        Product product;
        try {
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Product Not Found/ Invalid product Id");
        }
        if(product.getAvailableQuantity()<orderRequestDto.getRequiredQuantity() || product.getProductStatus()!= ProductStatus.AVAILABLE){
            throw new ProductOutOfStockException("Product is out of stock");
        }
        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        product.getItemList().add(item);
        return itemRepository.save(item);
    }
}
