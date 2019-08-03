package com.stackroute.recommendation.repository;

import com.stackroute.recommendation.domain.SubCategory;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SubCategoryRepository extends Neo4jRepository<SubCategory,String> {

    //query to create subcategrory
    @Query("CREATE (s:SubCategory) SET s.subCategoryName={subCategoryName} RETURN s")
    public SubCategory createNode(String subCategoryName);

    //query to get all subcategories
    @Query("MATCH (s:SubCategory) RETURN s")
    Collection<SubCategory> getAllSubCategories();

    //query to delete subcategory by subcategory name
    @Query("MATCH (s:SubCategory) WHERE s.subCategoryName={subCategoryName} DETACH DELETE s")
    public  SubCategory deleteNode(@Param("subCategory") String subCategory);
}
