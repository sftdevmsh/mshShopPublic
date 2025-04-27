package org.msh.repo.repositoryJpa.user;

import org.msh.repo.entity.user.PermissionEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepositoryJpa extends JpaRepository<PermissionEnt,Long> {
}
