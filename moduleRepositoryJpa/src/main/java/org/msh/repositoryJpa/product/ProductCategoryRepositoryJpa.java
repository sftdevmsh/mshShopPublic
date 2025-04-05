package org.msh.repositoryJpa.product;

import org.msh.entity.product.ProductCategoryEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepositoryJpa extends JpaRepository<ProductCategoryEnt, Long> {
    List<ProductCategoryEnt> findAllByEnabledIsTrueOrderByTitleAsc();
}
