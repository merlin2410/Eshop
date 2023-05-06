package com.Merlin.eshop.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SellerRequestDto {

    String name;

    int age;

    String email;

    String mobile;

    String enterprise;
}
