package com.stackroute.NLPservice.repository;

import com.stackroute.NLPservice.Domain.ProductRating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Productdetailrepository extends MongoRepository<ProductRating,String> {
}
