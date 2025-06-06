package org.msh.repo.repositoryJpa.user;

import org.msh.repo.entity.user.UserEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEnt,Long> {


    Optional<UserEnt> findByUsernameIgnoreCaseAndPassword(String username, String password);
    Optional<UserEnt> findByUsernameIgnoreCase(String username);

    @Query("from UserEnt u left join fetch u.roleEnts r left join fetch r.permissionEnts p where u.username = :username")
    Optional<UserEnt> findByUsernameIgnoreCaseQry(@Param("username") String username);
}
