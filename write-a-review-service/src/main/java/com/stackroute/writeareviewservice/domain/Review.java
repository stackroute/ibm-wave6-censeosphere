package com.stackroute.writeareviewservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    private String reviewed_by;
    private String review_title;
    private String review_description;
    private byte[] image;
    private String product_name;
    private Float price;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date reviewed_on;

}
