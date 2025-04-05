package org.msh.repositoryJpa.site;

import org.msh.entity.site.BlogEnt;
import org.msh.entity.site.ContentEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepositoryJpa extends JpaRepository<ContentEnt, Long>
{
    List<ContentEnt> findAllByOrderByTitleAsc();

}
