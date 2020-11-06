package com.example.webapi.repository;

import com.example.webapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    List<Product> findByProductId(Long productId);

    @Query(
            value = "select * from product t where state = :state",
            nativeQuery = true
    )
    List<Product> findActiveProducts(@Param("state") Integer state);

}
