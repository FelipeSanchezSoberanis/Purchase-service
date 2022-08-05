package com.exampleApp.purchaseService.entities;

import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product {

    private Long id;

    private String name;
    private String description;
    private Integer stock;
}
