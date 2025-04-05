package org.msh.repositoryJpa.payment;

import org.msh.entity.payment.PaymentEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepositoryJpa extends JpaRepository<PaymentEnt, Long>
{
}
