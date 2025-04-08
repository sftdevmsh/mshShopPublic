package org.msh.repositoryJpa.user;

import org.msh.entity.user.CustomerEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositoryJpa extends JpaRepository<CustomerEnt,Long> {
}
