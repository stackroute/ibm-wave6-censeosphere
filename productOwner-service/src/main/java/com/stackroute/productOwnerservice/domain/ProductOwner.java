package com.stackroute.productOwnerservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOwner {

    private String name;

    @Id
    private String emailId;
    private String image;
    private String role;
 //   private String password;
    private String reconfirmPassword;
  //private List<Productsuploaded> productsuploaded;

    @Override
    public String toString() {
        return "ProductOwner{" +
                "name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", image='" + image + '\'' +
                ", role='" + role + '\'' +
                ", reconfirmPassword='" + reconfirmPassword + '\'' +
                '}';
    }


}
