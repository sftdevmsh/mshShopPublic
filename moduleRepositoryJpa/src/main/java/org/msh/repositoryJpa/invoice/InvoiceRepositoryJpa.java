package org.msh.repositoryJpa.invoice;

import org.msh.entity.invoice.InvoiceEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepositoryJpa extends JpaRepository<InvoiceEnt, Long>
{
    Optional<InvoiceEnt> findFirstById(Long id);
    List<InvoiceEnt> findFirstByUserEnt_Id(Long id);

}
