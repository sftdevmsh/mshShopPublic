package org.msh.repo.repositoryJpa.payment;

import org.msh.repo.entity.payment.TransactionEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepositoryJpa extends JpaRepository<TransactionEnt,Long> {
    Optional<TransactionEnt> findFirstByAuthorityEqualsIgnoreCase(String authority);
}
