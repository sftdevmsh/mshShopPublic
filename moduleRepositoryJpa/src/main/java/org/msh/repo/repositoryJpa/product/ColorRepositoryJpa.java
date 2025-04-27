package org.msh.repo.repositoryJpa.product;

import org.msh.repo.entity.product.ColorEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepositoryJpa extends JpaRepository<ColorEnt, Long> {
}
