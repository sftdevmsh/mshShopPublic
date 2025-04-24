package org.msh.repositoryJpa.site;

import org.msh.entity.site.NavEnt;
import org.msh.entity.site.SliderEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SliderRepositoryJpa extends JpaRepository<SliderEnt, Long>
{
    List<SliderEnt> findAllByEnabledIsTrueOrderByOrderNumberAsc();

    Optional<SliderEnt> findFirstById(Long id);


    @Query("""
                select n.orderNumber 
                from SliderEnt n 
                order by n.orderNumber desc 
                limit 1
        """)
    Integer myFindLastOrderNumber();

    Optional<SliderEnt> findFirstByOrderNumberLessThanOrderByOrderNumberDesc(Integer orderNumber);
    Optional<SliderEnt> findFirstByOrderNumberGreaterThanOrderByOrderNumberAsc(Integer orderNumber);

}
