package org.msh.repo.repositoryJpa.file;

import org.msh.repo.entity.file.FileEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepositoryJpa extends JpaRepository<FileEnt, Long>
{
    Optional<FileEnt> findFirstByPathEqualsIgnoreCase(String name);
}
