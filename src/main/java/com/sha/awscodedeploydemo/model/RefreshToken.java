package com.sha.awscodedeploydemo.model;

import java.time.Instant;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "refreshtoken")
@Data
public class RefreshToken {
  @Id
  private long id;

  @DBRef
  private User user;

  private String token;

  private Instant expiryDate;

}
