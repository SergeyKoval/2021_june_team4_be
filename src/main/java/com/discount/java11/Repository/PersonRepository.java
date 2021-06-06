package com.discount.java11.Repository;

import com.discount.java11.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("select c from Person c " +
            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.secondName) like lower(concat('%', :searchTerm, '%'))")
    List<Person> findPersonByName(@Param("searchTerm") String searchTerm);

}
