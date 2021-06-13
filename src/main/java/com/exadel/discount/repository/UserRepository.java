package com.exadel.discount.repository;

import com.exadel.discount.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    @Query(value = "select a from users a where a.first_name/*'*/:=/*'*/('%searchTerm%') or a.last_name/*'*/:=/*'*/('%searchTerm%')", nativeQuery = true)
    List<User> findUsersByName(@Param("searchTerm") String searchTerm);

    void deleteById(UUID id);

    default User findUserById(UUID userId) {
        return findById(userId).get();
    }
}
