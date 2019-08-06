package com.stackroute.review.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class FakeReview
{

    String reviewerMail;
    private String reviewerEmail;
    private String reviewTitle;
    private String reviewDescription;
    private String productName;
    private float price;

    private Date reviewedOn;
    private String subCategory;
    private  int creditpoints;

    private String status;


}
