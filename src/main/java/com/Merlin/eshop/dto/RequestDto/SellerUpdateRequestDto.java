package com.Merlin.eshop.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerUpdateRequestDto {
    String email;
    String name;
    int age;
    String enterprise;
}
