package org.msh.repositoryJpa.user;

import org.msh.entity.user.RoleEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryJpa extends JpaRepository<RoleEnt,Long> {
}
