package com.stackroute.productsearchservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {

    private String category;
    private String subCategory;
    @Id
    private String productName;
    private String productFamily;
  private String image;
    private BigDecimal price;
   // private float rating;
    private String specifications;
    private String description;
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date uploadedOn;
}
