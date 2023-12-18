package com.streammanager.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class Overlay implements Serializable {
    
    @Id
    private String id;
    
    @NotNull
    private String name;
    
    private String techname;
    
    private String description;
    
    private boolean active;
}
