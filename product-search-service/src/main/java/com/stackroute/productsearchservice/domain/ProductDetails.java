package com.stackroute.productsearchservice.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "productsearch")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = ProductDetails.class)
public class ProductDetails {


    private String addedby;
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
