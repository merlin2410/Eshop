package com.Merlin.eshop.dto.ResponseDto;

import com.Merlin.eshop.Enum.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponseDto {

    String orderNumber;

    Date orderDate;

    String customerName;

    int numberOfItems;

    int totalCost;

    String cardUsed;

    OrderStatus orderStatus;

    List<ItemResponseDto> itemResponseDtoList;

}
