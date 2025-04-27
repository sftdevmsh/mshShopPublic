package org.msh.repo.repositoryJpa.product;

import org.msh.repo.entity.product.SizeEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepositoryJpa extends JpaRepository<SizeEnt, Long> {
}
