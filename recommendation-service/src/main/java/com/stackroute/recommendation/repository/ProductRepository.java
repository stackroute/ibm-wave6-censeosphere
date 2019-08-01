package com.stackroute.recommendation.repository;

import com.stackroute.recommendation.domain.Product;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface ProductRepository extends Neo4jRepository<Product,String> {

    @Query("MATCH (p:Product) RETURN p")
    Collection<Product> getAllProducts();

    @Query("CREATE (p:Product) SET p.productName={productName},p.rating={rating},p.price={price},p.productFamily={productFamily},p.subCategory={subCategory} RETURN p")
    public Product createNode(String productName, float rating, float price, String productFamily,String subCategory);

    @Query("MATCH (p:Product) WHERE p.productFamily={productFamily} RETURN p")
    Collection<Product> getNode(@Param("productFamily") String productFamily);

    @Query("MATCH (p:Product) WHERE p.subCategory={subCategory} RETURN p")
    Collection<Product> getBysubCategory(@Param("subCategory") String subCategory);

    @Query("MATCH (p:Product) WHERE p.productName={productName} DETACH DELETE p")
    public  Product deleteNode(@Param("productName") String productName);

    @Query("MATCH (m:Product),(n:SubCategory) WHERE m.productName={productName} and n.subCategory={subCategory} CREATE (m)-[r:isA]->(n) RETURN m")
    public Product createRelation(@Param("productName")String productName, @Param("subCategory")String subCategory);

    @Query("MATCH (r:Reviewer)-[:REVIEWS]->(p:Product)-[:isA]->"+"(s:SubCategory)<-[:isA]-(prod:Product) WHERE r.emailId={emailId}"+"RETURN prod")
    Collection<Product> getProduct(@Param("emailId")String emailId);

}
