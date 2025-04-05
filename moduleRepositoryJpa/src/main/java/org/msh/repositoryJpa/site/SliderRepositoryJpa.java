package org.msh.repositoryJpa.site;

import org.msh.entity.site.SliderEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SliderRepositoryJpa extends JpaRepository<SliderEnt, Long>
{
}
