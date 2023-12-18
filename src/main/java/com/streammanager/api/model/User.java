package com.streammanager.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@Data
public class User implements Serializable {
    
    @Id
    private String id;
    
    private String firstName;
    
    private String lastName;
    
    @NotNull
    private String email;

    @NotNull
    private String password;
    
    private String avatar;
}
