package com.stackroute.productOwnerservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOwner {
    private String name;
    @Id
    private String emailId;
    private String image;
    private String role;
    private String reconfirmPassword;
    private List<ProductDetails> productsadded;



}
