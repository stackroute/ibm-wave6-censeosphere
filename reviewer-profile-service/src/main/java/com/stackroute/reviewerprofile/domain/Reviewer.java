package com.stackroute.reviewerprofile.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "reviewer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reviewer
{
    @Id //Primary Key
    private String emailId;
    private String name;
    private String reconfirmPassword;
    private String role;
//    private byte[] image;
}
