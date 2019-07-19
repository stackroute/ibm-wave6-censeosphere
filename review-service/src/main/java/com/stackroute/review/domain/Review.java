package com.stackroute.review.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.stackroute.review.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Review.class)
public class Review {
    //primary key declaration

    private String reviewerEmail;
    private String reviewTitle;
    private String reviewDescription;
//    private byte[] image;
    private String productName;
    private Float price;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date reviewedOn;

}
