package com.example.webapi.repository;

import com.example.webapi.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {


}
