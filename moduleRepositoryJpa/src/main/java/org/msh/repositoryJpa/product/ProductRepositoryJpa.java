package org.msh.repositoryJpa.product;

import org.msh.entity.product.ProductEnt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepositoryJpa extends JpaRepository<ProductEnt, Long> {

    @Query(value = "select * from tbl_product where brand like CONCAT('%',:brand,'%')", nativeQuery = true)
    public Optional<List<ProductEnt>> findByBrandLike(@Param(value = "brand") String brand);

    @Query("""
        from ProductEnt p where p.exist = true
        and p.enabled = true
        order by p.addDate desc
        limit 6
    """)
    public List<ProductEnt> findTopLatest();

    @Query("""
        from ProductEnt p where p.exist = true
        and p.enabled = true
        order by p.visitCount desc
        limit 6
    """)
    public List<ProductEnt> findTopPopular();


    @Query("""
        from ProductEnt p where p.exist = true
        and p.enabled = true
        order by p.price desc
        limit 6
    """)
    public List<ProductEnt> findTopExpensive();


    @Query("""
        from ProductEnt p where p.exist = true
        and p.enabled = true
        order by p.price asc
        limit 6
    """)
    public List<ProductEnt> findTopCheap();


    @Query(value = "select p.id_product, p.price " +
            " from tbl_product p " +
            " where p.id_product in ( :ids )  " +
            " order by p.id_product asc"
            , nativeQuery = true)
    public Optional<HashMap<Long,Long>> getProductPrices(@Param("ids") List<Long> productIds);


    Page<ProductEnt> findAllByProductCategoryEnt_Id(Long productCategoryEntId, Pageable pageable);

}
