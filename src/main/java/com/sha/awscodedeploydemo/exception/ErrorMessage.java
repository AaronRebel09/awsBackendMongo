package com.sha.awscodedeploydemo.exception;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

  private int http_code;
  private Date date;
  private String message;
  private String description;
  
}
