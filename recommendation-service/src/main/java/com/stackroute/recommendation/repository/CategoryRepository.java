package com.stackroute.recommendation.repository;

import com.stackroute.recommendation.domain.Category;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CategoryRepository extends Neo4jRepository<Category,String> {

    //query to create category node
    @Query("CREATE (c:Category) SET c.categoryName={categoryName} RETURN c")
    public Category createNode(String category);

    //query to get all categories
    @Query("MATCH (c:Category) RETURN c")
    Collection<Category> getAllCategories();

    //query to delete category by category name
    @Query("MATCH (c:Category) WHERE c.categoryName={categoryName} DETACH DELETE c")
    public  Category deleteNode(@Param("categoryName") String categoryName);

    //query to create relation between category and subcategory
    @Query("MATCH (x:Category),(y:SubCategory) WHERE x.categoryName={categoryName} and y.subCategory={subCategory} CREATE (x)-[r:hasA]->(y) RETURN x")
    public Category createRelation(@Param("categoryName")String categoryName, @Param("subCategory")String subCategory);
}
