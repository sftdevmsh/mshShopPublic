package org.msh.repo.repositoryJpa.site;

import org.msh.repo.entity.site.ContentEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepositoryJpa extends JpaRepository<ContentEnt, Long>
{
    List<ContentEnt> findAllByOrderByTitleAsc();
    Optional<ContentEnt> findFirstByTitleEqualsIgnoreCase(String key);

    Optional<ContentEnt> findFirstById(Long id);
}
