package com.exadel.discount.repository;

import com.exadel.discount.entity.Role;
import com.exadel.discount.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);

    @Query(value = "select u from User u where u.role = :r", countQuery = "select count(u) from User u where u.role = :r")
    Page<User> roleSearch(@Param("r") Role r, Pageable pageable);

    @Query(value = "select u from User u join fetch u.city c where c.name like %?1%", countQuery = "select count(u) from User u join fetch u.city c where c.name like %?1%")
    Page<User> citySearch(@Param("c")String city, Pageable pageable);

    @Query(value = "select u from User u join fetch u.city.country ct where ct.name like %?1%", countQuery = "select count(u) from User u join fetch u.city.country ct where ct.name like %?1%")
    Page<User> countrySearch(@Param("ct")String country, Pageable pageable);

    List<User> findDistinctByLastNameAndFirstName(String lastName, String firstName);
}
