package org.msh.repositoryJpa.product;

import org.msh.entity.product.ProductEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepositoryJpa extends JpaRepository<ProductEnt, Long> {

    public Optional<ProductEnt> findBySKUEqualsIgnoreCase(String sku);


    @Query(value = "select * from tbl_product where brand like CONCAT('%',:brand,'%')", nativeQuery = true)
    public Optional<List<ProductEnt>> findByBrandLike(@Param(value = "brand") String brand);

}
