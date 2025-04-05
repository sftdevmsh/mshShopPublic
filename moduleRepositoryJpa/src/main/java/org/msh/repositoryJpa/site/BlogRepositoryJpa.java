package org.msh.repositoryJpa.site;

import org.msh.entity.site.BlogEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepositoryJpa extends JpaRepository<BlogEnt, Long>
{
    List<BlogEnt> findAllByOrderByTitleAsc();
}
