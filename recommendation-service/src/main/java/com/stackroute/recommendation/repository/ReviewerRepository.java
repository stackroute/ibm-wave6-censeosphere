package com.stackroute.recommendation.repository;

import com.stackroute.recommendation.domain.Reviewer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ReviewerRepository extends Neo4jRepository<Reviewer,String> {

    //query to get all reviewer
    @Query("MATCH (r:Reviewer) RETURN r")
    Collection<Reviewer> getAllReviewers();

    //query to create reviewer node
    @Query("CREATE (r:Reviewer) SET r.emailId={emailId} RETURN r")
    public Reviewer createNode(String emailId);

    //query to delete reviewer by emailId
    @Query("MATCH (r:Reviewer) WHERE r.emailId={emailId} DETACH DELETE r")
    public Reviewer deleteNode(String emailId);

    //query to get reviewer by emailId
    @Query("MATCH (r:Reviewer) WHERE r.emailId={emailId} RETURN r")
    public Reviewer getNode(@Param("emailId") String emailId);

    //query to create relation between reviewer and product
    @Query("MATCH (a:Reviewer),(b:Product) WHERE a.emailId={emailId} and b.productName={productName} CREATE (a)-[r:REVIEWS]->(b) RETURN a")
    public Reviewer createRelation(@Param("emailId")String emailId, @Param("productName")String  productName);

}
