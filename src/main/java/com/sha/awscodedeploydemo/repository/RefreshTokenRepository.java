package com.sha.awscodedeploydemo.repository;


import com.sha.awscodedeploydemo.model.RefreshToken;
import com.sha.awscodedeploydemo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, Long> {

  Optional<RefreshToken> findById(Long id);

  Optional<RefreshToken> findByToken(String token);
  
  int deleteByUser(User user);
}
