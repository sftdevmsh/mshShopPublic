package org.msh.repositoryJpa.site;

import org.msh.entity.site.BlogEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepositoryJpa extends JpaRepository<BlogEnt, Long>
{
}
