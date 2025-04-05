package org.msh.repositoryJpa.file;

import org.msh.entity.file.FileEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepositoryJpa extends JpaRepository<FileEnt, Long>
{
}
