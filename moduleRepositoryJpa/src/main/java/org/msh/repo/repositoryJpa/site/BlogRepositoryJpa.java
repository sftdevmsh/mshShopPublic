package org.msh.repo.repositoryJpa.site;

import org.msh.repo.entity.site.BlogEnt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepositoryJpa extends JpaRepository<BlogEnt, Long>
{
    //List<BlogEnt> findAllByOrderByTitleAsc(Pageable pageable);

    @Query("""
        from BlogEnt b where b.blogStatus = msh.controller.enums.BlogStatus.Published
            and b.publishDate <= current_date
            order by b.publishDate desc
    """)
    Page<BlogEnt> myFindAllPublished(Pageable pageable);

    Optional<BlogEnt> findFirstByTitleEqualsIgnoreCase(String title);

    Optional<BlogEnt> findFirstById(Long id);
}
