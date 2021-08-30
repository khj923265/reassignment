package com.rb.rbassignment.repository;

import com.rb.rbassignment.domain.AccessToken;
import org.springframework.data.repository.CrudRepository;

public interface AccessTokenRedisRepository extends CrudRepository<AccessToken, String> {
}
