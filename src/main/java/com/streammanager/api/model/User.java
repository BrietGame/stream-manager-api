package com.streammanager.api.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "users")
public class User implements Serializable {
    
    @Id
    private String id;
    
    private String firstName;
    
    private String lastName;
    
    private String email;

    private String username;

    private String password;
    
    private String avatar;
    
    private List<Role> roles;
    
    private String refreshToken;
}
