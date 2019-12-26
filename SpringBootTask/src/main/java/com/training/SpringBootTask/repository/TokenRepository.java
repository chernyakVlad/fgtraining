package com.training.SpringBootTask.repository;

import com.training.SpringBootTask.entity.authentication.JwtToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TokenRepository extends MongoRepository<JwtToken, String> {
    Optional<JwtToken> findByAccessToken(String accessToken);

}
