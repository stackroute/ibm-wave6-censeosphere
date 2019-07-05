package com.stackroute.reviewerprofile.dto;

import org.springframework.data.annotation.Id;

public class ReviewerDto
{
    private String name;
    @Id //Primary Key
    private String emailId;
    private String password;
    private String reconfirmPassword;
}
