package com.Merlin.eshop.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerRequestDto {
    String name;
    int age;
    String mobile;
    String email;
    String address;


}
