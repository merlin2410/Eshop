package com.Merlin.eshop.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerResponseDto {

    String name;

    int age;

    String enterprise;
}
