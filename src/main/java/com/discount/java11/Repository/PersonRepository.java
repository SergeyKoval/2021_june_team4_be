package com.discount.java11.Repository;

import com.discount.java11.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
//    @Query("select c from Persons c " +
//            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
//            "or lower(c.secondName) like lower(concat('%', :searchTerm, '%'))")
//    List<Person> findPersonByName(@Param("searchTerm") String searchTerm);

    @Query(value="select * from persons a where a.first_name= :('%', :searchTerm, '%') or a:second_name= :('%', :searchTerm, '%')", nativeQuery=true)
    List<Person> findPersonByName(@Param("searchTerm") String searchTerm);
}
