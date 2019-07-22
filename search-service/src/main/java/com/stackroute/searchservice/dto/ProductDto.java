package com.stackroute.searchservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

    private String productName;
    private String subCategory;
    private String productFamily;
    private String image;
    private Float price;
    private int rating;
    private String specifications;
    private String description;
    private Date uploadedOn;
}
