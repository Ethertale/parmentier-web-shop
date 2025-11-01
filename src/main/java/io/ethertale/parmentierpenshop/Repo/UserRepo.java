package io.ethertale.parmentierpenshop.Repo;

import io.ethertale.parmentierpenshop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    Optional<User> findUserByUsername(String username);
}
