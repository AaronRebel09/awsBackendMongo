package com.sha.awscodedeploydemo.repository;

import java.util.Optional;

import com.sha.awscodedeploydemo.model.ERole;
import com.sha.awscodedeploydemo.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
