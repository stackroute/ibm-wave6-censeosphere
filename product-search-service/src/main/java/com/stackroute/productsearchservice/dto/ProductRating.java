package com.stackroute.productsearchservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRating {
    @Id
    String productName;
    float rating;
    float sum;
    int count;

}

