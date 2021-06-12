package com.exadel.discount.repository;

import com.exadel.discount.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends CrudRepository<Person, UUID> {
    @Query(value = "select a from persons a where a.first_name= :('%searchTerm%') or a.last_name= :('%searchTerm%')", nativeQuery = true)
    List<Person> findPersonByName(@Param("searchTerm") String searchTerm);
}
