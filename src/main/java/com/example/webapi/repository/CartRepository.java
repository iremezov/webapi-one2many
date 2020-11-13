package com.example.webapi.repository;

import com.example.webapi.model.Cart;
import com.example.webapi.model.Person;
import com.example.webapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    //this also work, but without checking status
    //Optional<List<Cart>> findByPerson(Person person);

    @Query(
            value = "select * from cart t where state = 1 and t.person_id = :id",
            nativeQuery = true
    )
    Optional<List<Cart>> findByPerson(@Param("id") Long id);

}
