package com.Merlin.eshop.dto.RequestDto;

import com.Merlin.eshop.Enum.ProductCategory;
import com.Merlin.eshop.Enum.ProductStatus;
import com.Merlin.eshop.models.Seller;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {

    String name;

    int availableQuantity;

    int price;

    ProductCategory category;

    int sellerId;
}
