package com.example.webapi.controller;

import com.example.webapi.model.Catalog;
import com.example.webapi.repository.CatalogRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @Autowired
    private CatalogRepository catalogRepository;

    @ApiOperation("Добавляется/редактируется каталог.")
    @RequestMapping(value = "/addCatalog", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    Catalog addNewCatalog (@ApiParam("Информация о каталоге id, name.")
                           @RequestBody Catalog input){
        catalogRepository.save(input);
        return input;
    }

    @ApiOperation("Получает имя каталога по его ИД")
    @RequestMapping(value = "/getCatalogById", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Catalog> getCatalogById(@RequestBody Catalog input){
        return new ResponseEntity<Catalog>(catalogRepository.findById(input.getId()).get(), HttpStatus.OK);
    }

    @ApiOperation("Взвращает список имеющихся каталлогов")
    @GetMapping("/getAllCatalogs")
    public List<Catalog> getAllCatalogs() {
        return catalogRepository.findAll();
    }





}
