package io.ethertale.parmentierpenshop.Repo;

import io.ethertale.parmentierpenshop.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, UUID> {
}
