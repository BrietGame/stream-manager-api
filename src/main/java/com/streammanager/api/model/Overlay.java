package com.streammanager.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
public class Overlay {
    
    @Id
    private String id;
    
    @NotNull
    private String name;
    
    private String techname;
    
    private String description;
    
    private boolean active;
}
