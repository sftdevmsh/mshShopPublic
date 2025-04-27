package org.msh.repo.repositoryJpa.user;

import org.msh.repo.entity.user.CustomerEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositoryJpa extends JpaRepository<CustomerEnt,Long> {
}
