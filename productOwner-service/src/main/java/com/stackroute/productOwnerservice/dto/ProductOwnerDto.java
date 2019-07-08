package com.stackroute.productOwnerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOwnerDto {

    private String name;
    @Id
    private String emailId;
    private String password;
    private String confirmPassword;

}
