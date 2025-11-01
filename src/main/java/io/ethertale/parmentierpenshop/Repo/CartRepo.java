package io.ethertale.parmentierpenshop.Repo;

import io.ethertale.parmentierpenshop.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepo extends JpaRepository<Cart, UUID> {
}
