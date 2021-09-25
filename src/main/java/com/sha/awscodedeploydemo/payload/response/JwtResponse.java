package com.sha.awscodedeploydemo.payload.response;

import java.util.List;
import lombok.Data;

@Data
public class JwtResponse {
  
  private String accessToken;
  private String tokenType = "Bearer";
  private String refreshToken;
  private String id;
  private String username;
  private String email;
  private List<String> roles;

  public JwtResponse(String accessToken, String refreshToken, String id, String username, String email, List<String> roles) {
      this.accessToken = accessToken;
      this.refreshToken = refreshToken;
      this.id = id;
      this.username = username;
      this.email = email;
      this.roles = roles;
  }
  
}
