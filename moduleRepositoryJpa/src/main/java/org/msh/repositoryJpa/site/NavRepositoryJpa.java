package org.msh.repositoryJpa.site;

import org.msh.entity.site.NavEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NavRepositoryJpa extends JpaRepository<NavEnt, Long>
{
}
