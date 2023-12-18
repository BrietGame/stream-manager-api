package com.streammanager.api.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "overlays")
public class Overlay implements Serializable {
    
    @Id
    private String id;
    
    @NonNull
    private String name;
    
    private String techname;
    
    private String description;
    
    private boolean active;
}
