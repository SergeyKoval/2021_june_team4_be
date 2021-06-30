package com.exadel.discount.repository;

import com.exadel.discount.entity.Role;
import com.exadel.discount.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    //@Query(value = "SELECT u FROM User u WHERE u.role = :r", countQuery = "SELECT count(u) FROM User u WHERE u.role = :r", nativeQuery = true)
    //Page<User> roleSearch(@Param("r") Role r, Pageable pageable);

    Page<User> findUserByRole( Role r, Pageable pageable);


    //@Query(value = "SELECT u FROM User u JOIN FETCH u.city c WHERE c.name LIKE %:city%", countQuery = "SELECT count(u) FROM User u JOIN u.city c WHERE c.name LIKE %:city%", nativeQuery = true)
    //Page<User> findUsersByCity_Name(Param("city")String city, Pageable pageable);
    @EntityGraph(attributePaths = {"city"})
    Page<User> findUsersByCity_Name(String city, Pageable pageable);

   // @Query(value = "SELECT u FROM User u INNER JOIN FETCH u.city.country ct WHERE ct.name LIKE %:ct%", countQuery = "SELECT count(u) FROM User u INNER JOIN u.city.country ct WHERE ct.name LIKE %:ct%", nativeQuery = true)
    //Page<User> countrySearch(@Param("ct")String ct, Pageable pageable);
    @EntityGraph(attributePaths = {"country"})
    Page<User> findUsersByCity_Country_Name(String ct, Pageable pageable);

    List<User> findDistinctByLastNameAndFirstName(String lastName, String firstName);
}
