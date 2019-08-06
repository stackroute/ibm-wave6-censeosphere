package com.stackroute.review.repository;

import com.stackroute.review.domain.FakeReview;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FakeReviewRepository extends MongoRepository<FakeReview,String>
{
}
