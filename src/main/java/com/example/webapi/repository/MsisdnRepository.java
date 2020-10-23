package com.example.webapi.repository;

import com.example.webapi.model.Msisdn;
import com.example.webapi.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.print.Book;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface MsisdnRepository extends JpaRepository<Msisdn, Long> {

    //List<Person> findByPersonList(Long personId);
    //Page<Comment> findByPostId(Long postId, Pageable pageable);
    List<Msisdn> findByPersonId(Long personId);

}
