package io.ethertale.parmentierpenshop.Repo;

import io.ethertale.parmentierpenshop.Model.Pen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PenRepo extends JpaRepository<Pen, UUID> {
}
