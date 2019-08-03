package com.stackroute.reviewerprofile.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Builder
@Document(collection = "reviewer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private String reviewerEmail;
    private String reviewTitle;
    private String reviewDescription;
    private String productName;
    private Float price;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date reviewedOn;
}
