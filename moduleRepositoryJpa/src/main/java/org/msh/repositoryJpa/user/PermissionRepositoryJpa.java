package org.msh.repositoryJpa.user;

import org.msh.entity.user.PermissionEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepositoryJpa extends JpaRepository<PermissionEnt,Long> {
}
