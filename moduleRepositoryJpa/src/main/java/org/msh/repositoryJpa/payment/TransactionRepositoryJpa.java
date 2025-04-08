package org.msh.repositoryJpa.payment;

import org.msh.entity.payment.TransactionEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepositoryJpa extends JpaRepository<TransactionEnt,Long> {
}
