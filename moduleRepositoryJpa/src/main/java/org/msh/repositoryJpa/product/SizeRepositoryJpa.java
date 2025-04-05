package org.msh.repositoryJpa.product;

import org.msh.entity.product.SizeEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepositoryJpa extends JpaRepository<SizeEnt, Long> {
}
