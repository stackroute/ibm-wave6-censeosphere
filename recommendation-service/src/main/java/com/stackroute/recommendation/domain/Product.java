package com.stackroute.recommendation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;


@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class Product {
    @Id
    private String productName;
    private float rating;
    private float price;
    private String productFamily;
    private String subCategory;

}
