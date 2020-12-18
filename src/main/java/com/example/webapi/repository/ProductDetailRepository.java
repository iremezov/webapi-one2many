package com.example.webapi.repository;

import com.example.webapi.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    //List<ProductProp> findByProductId(Integer productId);

}
