package org.msh.repo.repositoryJpa.product;

import org.msh.repo.entity.product.ProductCategoryEnt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepositoryJpa extends JpaRepository<ProductCategoryEnt, Long> {
    Page<ProductCategoryEnt> findAllByEnabledIsTrueOrderByTitleAsc(Pageable pageable);
}
