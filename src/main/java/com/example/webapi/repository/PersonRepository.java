package com.example.webapi.repository;

import com.example.webapi.model.Msisdn;
import com.example.webapi.model.Person;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.awt.print.Book;
import java.util.Collection;
import java.util.List;

@Transactional
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    //Collection<Person> findbyId(@Param("id") Long id);

    @Query(
            value = "select * from persons t where t.id = :id",
            nativeQuery = true
    )
    Collection<Person> findOneItem(@Param("id") Long id);

    //List<Msisdn> findByPersonId(Long personId);


}