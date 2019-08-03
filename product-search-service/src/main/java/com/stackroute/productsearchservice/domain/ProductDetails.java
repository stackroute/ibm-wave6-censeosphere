package com.stackroute.productsearchservice.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
    private float price;
    private float rating;
    private String specifications;
    private String description;
    private Date uploadedOn;
}
