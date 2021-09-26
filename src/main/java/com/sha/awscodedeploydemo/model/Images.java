package com.sha.awscodedeploydemo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "images")
public class Images {

    @Id
    private String id;
    private String title;
    private String description;
    //private String imagePath;
    //private String imageFileName;

    @DBRef
    private User user;

    @DBRef
    private List<Image> images = new ArrayList<>();

}
