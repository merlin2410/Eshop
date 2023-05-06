package com.Merlin.eshop.dto.ResponseDto;

import com.Merlin.eshop.Enum.ProductCategory;
import com.Merlin.eshop.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDto {

    String name;

    int price;

    ProductCategory productCategory;

    ProductStatus productStatus;
}
