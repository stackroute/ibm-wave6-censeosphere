package com.stackroute.reviewerprofile.repository;

import com.stackroute.reviewerprofile.domain.Reviewer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewerRepository extends MongoRepository<Reviewer,String> {
}
