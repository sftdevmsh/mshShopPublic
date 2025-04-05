package org.msh.repositoryJpa.site;

import org.msh.entity.site.NavEnt;
import org.msh.entity.site.SliderEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SliderRepositoryJpa extends JpaRepository<SliderEnt, Long>
{
    List<SliderEnt> findAllByEnabledIsTrueOrderByOrderNumberAsc();

}
