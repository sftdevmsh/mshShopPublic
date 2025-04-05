package org.msh.repositoryJpa.site;

import org.msh.entity.site.ContentEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepositoryJpa extends JpaRepository<ContentEnt, Long>
{
}
