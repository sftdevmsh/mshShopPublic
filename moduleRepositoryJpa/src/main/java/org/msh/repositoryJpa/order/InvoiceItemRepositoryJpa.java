package org.msh.repositoryJpa.order;

import org.msh.entity.order.InvoiceItemEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItemRepositoryJpa extends JpaRepository<InvoiceItemEnt, Long>
{
}
