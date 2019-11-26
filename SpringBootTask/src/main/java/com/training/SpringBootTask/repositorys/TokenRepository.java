package com.training.SpringBootTask.repositorys;

import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.models.authentication.JwtToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TokenRepository extends MongoRepository<JwtToken, String> {
    Optional<JwtToken> findByAccessToken(String accessToken);

}
