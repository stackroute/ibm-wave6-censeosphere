package com.stackroute.searchservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Products  {
    @Id
    private String productName;
    private String productFamily;
    private String image;
    private Float price;
    private int rating;
    private String specifications;
    private String description;
    private Date uploadedOn;

}
