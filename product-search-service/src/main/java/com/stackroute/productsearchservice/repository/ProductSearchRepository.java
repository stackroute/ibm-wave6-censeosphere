package com.stackroute.productsearchservice.repository;

import com.stackroute.productsearchservice.domain.ProductDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSearchRepository extends MongoRepository<ProductDetails,String> {

}
