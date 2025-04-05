package org.msh.repositoryJpa.order;

import org.msh.entity.order.InvoiceEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepositoryJpa extends JpaRepository<InvoiceEnt, Long>
{
}
