package com.stackroute.productOwnerservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ProductDetails {
    private  String addedby;
    private String category;
    private String subCategory;
    @Id
    private String productName;
    private String productFamily;
    private String image;
    private Float price;
    private int rating;
    private String specifications;
    private String description;
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date uploadedOn;
}
