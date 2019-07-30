package com.stackroute.searchservice.service;

import com.stackroute.searchservice.domain.Products;
import com.stackroute.searchservice.domain.Subcategory;
import com.stackroute.searchservice.dto.ProductDto;
import com.stackroute.searchservice.exception.SubcategoryAlreadyExistsExceptions;
import com.stackroute.searchservice.exception.SubcategoryNotFoundException;
import com.stackroute.searchservice.repository.SubcategoryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//function implementation
@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    List<Products> productsList= new ArrayList<>();

    Products products;

    Subcategory subcategory;

    @Autowired
    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository1)
    {
        this.subcategoryRepository=subcategoryRepository1;
    }


    @Override
    public Subcategory saveSubcategory(Subcategory subcategory) throws SubcategoryAlreadyExistsExceptions {
        if (subcategoryRepository.existsById(subcategory.getSubCategory()))
        {

            throw new SubcategoryAlreadyExistsExceptions("Subcategory already exists!");
        }
        else
        {
           Subcategory savedSubcategory=subcategoryRepository.save(subcategory);
          return subcategory;
        }
    }


    @Override
    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    @Override
    public List<Products> findAllProductsBySubcategory(String subCategory) throws SubcategoryNotFoundException {
        Subcategory fetchedSubCategory = subcategoryRepository.findBySubCategory(subCategory);
        if(fetchedSubCategory!= null){
            return fetchedSubCategory.getProducts();
        }
        else{
            throw new SubcategoryNotFoundException("SubCategory not found");
        }

//        if (subcategoryRepository.existsById(subcategory.getSubCategory()))
//        {
//       productsList = subcategoryRepository.findBySubCategory(subCategory);
//
//        }
//        return productsList;
    }

    @Override
    public String deleteSubcategory(String subCategory) throws SubcategoryNotFoundException {
        if(subcategoryRepository.existsById(subCategory))
        {
            subcategoryRepository.deleteById(subCategory);
            return "Subcategory deleted successfully!";
        }
        else {
            throw new SubcategoryNotFoundException("Details not found!");
        }

    }

    @RabbitListener(queues="${stackroute.rabbitmq.queueSix}")
    public Subcategory updateSubcategory(ProductDto productDto) {
        System.out.println("recieved product= " + productDto.toString());

        Subcategory newSubcategory=new Subcategory();
        Products newProduct = new Products();
        Subcategory fetchedSubcategory= subcategoryRepository.findBySubCategory(productDto.getSubCategory());

        if(fetchedSubcategory!=null){

            // fetchedSubcategory.setProducts(subcategory.getProducts());
            List<Products> newProductsList = fetchedSubcategory.getProducts();

            newProduct.setUploadedOn(productDto.getUploadedOn());
            newProduct.setPrice(productDto.getPrice());
            newProduct.setSpecifications(productDto.getSpecifications());
            newProduct.setDescription(productDto.getDescription());
            newProduct.setRating(productDto.getRating());
            newProduct.setProductFamily(productDto.getProductFamily());
            newProduct.setProductName(productDto.getProductName());
            newProduct.setImage(productDto.getImage());
            newProductsList.add(newProduct);
            fetchedSubcategory.setProducts(newProductsList);
            subcategoryRepository.save(fetchedSubcategory);
            return fetchedSubcategory;
        }
    else {
        Subcategory fetchedSubcategory1=new Subcategory();
        fetchedSubcategory1.setSubCategory(productDto.getSubCategory());
        subcategoryRepository.save(fetchedSubcategory1);
         newProduct.setUploadedOn(productDto.getUploadedOn());
         newProduct.setPrice(productDto.getPrice());
         newProduct.setSpecifications(productDto.getSpecifications());
         newProduct.setDescription(productDto.getDescription());
         newProduct.setRating(productDto.getRating());

         newProduct.setProductFamily(productDto.getProductFamily());
         newProduct.setProductName(productDto.getProductName());
         newProduct.setImage(productDto.getImage());
         productsList.add(newProduct);
         fetchedSubcategory1.setProducts(productsList);
         subcategoryRepository.save(fetchedSubcategory1);
            return fetchedSubcategory1;
        }

    }
}
