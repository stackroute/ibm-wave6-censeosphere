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
import java.util.Optional;

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


        System.out.println("recieved msg  after update rating= " + productDetails.toString());
        Subcategory fetchedSubcategory= subcategoryRepository.findBySubCategory(productDetails.getSubCategory());
        System.out.println(fetchedSubcategory);
        if(fetchedSubcategory!=null){
            System.out.println("inside  update rating");
            List<Products> newProductsList = subcategoryRepository.findBySubCategory(productDetails.getSubCategory()).getProducts();
            System.out.println("before loop");
            for (int i=0;i< newProductsList.size();i++){
                System.out.println("inside for loop");
                if(((newProductsList.get(i)).getProductName()).equals(productDetails.getProductName())) {
                    System.out.println("insides equal");
                    (newProductsList.get(i)).setRating(productDetails.getRating());
                    fetchedSubcategory.setProducts(newProductsList);
                    subcategoryRepository.save(fetchedSubcategory);
                }
            }

        }


    }

    @RabbitListener(queues="${stackroute.rabbitmq.queueten}")
    public void updateSubcategory(ProductDetails productDetails) {
        System.out.println("recieved product= " + productDetails.toString());

        Subcategory newSubcategory=new Subcategory();
        Products newProduct = new Products();
        Subcategory fetchedSubcategory= subcategoryRepository.findBySubCategory(productDetails.getSubCategory());

        if(fetchedSubcategory!=null){

            System.out.println("hai");
            // fetchedSubcategory.setProducts(subcategory.getProducts());
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

         System.out.println("bai");
         Subcategory fetchedSubcategory1=new Subcategory();
         fetchedSubcategory1.setSubCategory(productDetails.getSubCategory());
         subcategoryRepository.save(fetchedSubcategory1);
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
