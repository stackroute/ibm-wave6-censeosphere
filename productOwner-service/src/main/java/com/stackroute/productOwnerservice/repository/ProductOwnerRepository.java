package com.stackroute.productOwnerservice.repository;

import com.stackroute.productOwnerservice.domain.ProductOwner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOwnerRepository extends MongoRepository<ProductOwner,String> {

}
