package com.sha.awscodedeploydemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.security.Timestamp;

@Document(collection = "images")
@Data
@NoArgsConstructor
public class Image {
    @Id
    private String id;

    private String name;

    private String path;

    private Timestamp timestamp;

    private Object label;

}
