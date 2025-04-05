package org.msh.repositoryJpa.site;

import org.msh.entity.site.BlogEnt;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepositoryJpa extends JpaRepository<BlogEnt, Long>
{
    //List<BlogEnt> findAllByOrderByTitleAsc(Pageable pageable);

    @Query("""
        from blog b where b.blogStatus = org.msh.enums.BlogStatus.Published
            and b.publishDate <= current_date
            order by b.publishDate desc
    """)
    List<BlogEnt> myFindAllPublished(Pageable pageable);

    Optional<BlogEnt> findFirstByTitleEqualsIgnoreCase(String title);
}
