package com.stackroute.recommendation.repository;

import com.stackroute.recommendation.domain.Category;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CategoryRepository extends Neo4jRepository<Category,String> {

    @Query("CREATE (c:Category) SET c.categoryName={categoryName} RETURN c")
    public Category createNode(String category);

    @Query("MATCH (c:Category) RETURN c")
    Collection<Category> getAllCategories();

    @Query("MATCH (c:Category) WHERE c.categoryName={categoryName} DETACH DELETE c")
    public  Category deleteNode(@Param("categoryName") String categoryName);

    @Query("MATCH (x:Category),(y:SubCategory) WHERE x.categoryName={categoryName} and y.subCategory={subCategory} CREATE (x)-[r:hasA]->(y) RETURN x")
    public Category createRelation(@Param("categoryName")String categoryName, @Param("subCategory")String subCategory);
}
