package com.training.SpringBootTask.repositorys;

import com.training.SpringBootTask.models.Role;
import com.training.SpringBootTask.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByRole(String role);
}
