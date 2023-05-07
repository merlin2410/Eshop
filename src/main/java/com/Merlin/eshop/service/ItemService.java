package com.Merlin.eshop.service;

import com.Merlin.eshop.dto.RequestDto.ItemRequestDto;
import com.Merlin.eshop.exception.ProductNotFoundException;
import com.Merlin.eshop.models.Item;

public interface ItemService {

    public Item addItem(ItemRequestDto itemRequestDto) throws Exception;
}
