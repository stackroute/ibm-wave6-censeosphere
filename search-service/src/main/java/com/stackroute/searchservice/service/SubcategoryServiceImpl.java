package com.stackroute.searchservice.service;

import com.stackroute.searchservice.domain.Products;
import com.stackroute.searchservice.domain.Subcategory;
import com.stackroute.searchservice.dto.ProductDetails;
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
        if (subcategoryRepository.existsById(subcategory.getSubCategoryName()))
        {

            throw new SubcategoryAlreadyExistsExceptions("Subcategory already exists!");
        }
        else
        {
             return subcategoryRepository.save(subcategory);
        }
    }


    @Override
    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    @Override
    public List<Products> findAllProductsBySubcategory(String subCategory) throws SubcategoryNotFoundException {
        Subcategory fetchedSubCategory = subcategoryRepository.findBySubCategoryName(subCategory);
        if(fetchedSubCategory!= null){
            return fetchedSubCategory.getProducts();
        }
        else
            {
            throw new SubcategoryNotFoundException("SubCategory not found");
            }


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


    @RabbitListener(queues="${stackroute.rabbitmq.queueeleven}")
    public void  Recieverating(ProductDetails productDetails)
    {

        Subcategory fetchedSubcategory= subcategoryRepository.findBySubCategoryName(productDetails.getSubCategory());

        if(fetchedSubcategory!=null){

            List<Products> newProductsList = fetchedSubcategory.getProducts();

            for (int i=0;i< newProductsList.size();i++){

                if(((newProductsList.get(i)).getProductName()).equals(productDetails.getProductName())) {

                    (newProductsList.get(i)).setRating(productDetails.getRating());
                    fetchedSubcategory.setProducts(newProductsList);
                    subcategoryRepository.save(fetchedSubcategory);
                }
            }

        }


    }

    @RabbitListener(queues="${stackroute.rabbitmq.queueten}")
    public void updateSubcategory(ProductDetails productDetails) {

        Products newProduct = new Products();
        Subcategory fetchedSubcategory= subcategoryRepository.findBySubCategoryName(productDetails.getSubCategory());

        if(fetchedSubcategory!=null){


            List<Products> newProductsList = fetchedSubcategory.getProducts();
            newProduct.setUploadedOn(productDetails.getUploadedOn());
            newProduct.setPrice(productDetails.getPrice());
            newProduct.setSpecifications(productDetails.getSpecifications());
            newProduct.setDescription(productDetails.getDescription());
            newProduct.setRating(productDetails.getRating());
            newProduct.setProductFamily(productDetails.getProductFamily());
            newProduct.setProductName(productDetails.getProductName());
            newProduct.setImage(productDetails.getImage());
            newProductsList.add(newProduct);
            fetchedSubcategory.setProducts(newProductsList);
            subcategoryRepository.save(fetchedSubcategory);

         }
    else {

         Subcategory fetchedSubcategory1=new Subcategory();
         fetchedSubcategory1.setSubCategoryName(productDetails.getSubCategory());
         newProduct.setUploadedOn(productDetails.getUploadedOn());
         newProduct.setPrice(productDetails.getPrice());
         newProduct.setSpecifications(productDetails.getSpecifications());
         newProduct.setDescription(productDetails.getDescription());
         newProduct.setRating(productDetails.getRating());
         newProduct.setProductFamily(productDetails.getProductFamily());
         newProduct.setProductName(productDetails.getProductName());
         newProduct.setImage(productDetails.getImage());
         productsList.add(newProduct);
         fetchedSubcategory1.setProducts(productsList);
         subcategoryRepository.save(fetchedSubcategory1);

        }

    }


}
