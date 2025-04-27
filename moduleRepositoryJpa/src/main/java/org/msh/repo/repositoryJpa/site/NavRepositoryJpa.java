package org.msh.repo.repositoryJpa.site;

import org.msh.repo.entity.site.NavEnt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NavRepositoryJpa extends JpaRepository<NavEnt, Long>
{
    List<NavEnt> findAllByEnabledIsTrueOrderByOrderNumberAsc();
    Page<NavEnt> findAllByEnabledIsTrueOrderByOrderNumberAsc(Pageable pageable);

    @Query("""
                select n.orderNumber 
                from NavEnt n 
                order by n.orderNumber desc 
                limit 1
        """)
    Integer myFindLastOrderNumber();

    Optional<NavEnt> findFirstById(Long id);

    Optional<NavEnt> findFirstByOrderNumberLessThanOrderByOrderNumberDesc(Integer orderNumber);
    Optional<NavEnt> findFirstByOrderNumberGreaterThanOrderByOrderNumberAsc(Integer orderNumber);


}
