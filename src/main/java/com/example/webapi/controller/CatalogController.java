package com.example.webapi.controller;

import com.example.webapi.model.Catalog;
import com.example.webapi.model.Person;
import com.example.webapi.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class CatalogController {

    @Autowired
    private CatalogRepository catalogRepository;

    @RequestMapping(value = "/addCatalog", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    String addNewCatalog (@RequestBody Catalog input){
        catalogRepository.save(input);
        return "Saved";
    }

    @RequestMapping(value = "/getProductsByCatalogId", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Catalog> getProductsByPersonId(@RequestBody Catalog input){
        return new ResponseEntity<Catalog>(catalogRepository.findById(input.getId()).get(), HttpStatus.OK);
    }



}
