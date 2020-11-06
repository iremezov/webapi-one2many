package com.example.webapi.controller;

import com.example.webapi.model.Msisdn;
import com.example.webapi.model.Person;
import com.example.webapi.model.Product;
import com.example.webapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public List<Product> getAllProducts() {
        //return productRepository.findAll();
        return productRepository.findActiveProducts(1);
    }


    @RequestMapping(value = "/addProduct", method = RequestMethod.POST, headers = "Accept=application/json")
    @Transactional
    @javax.transaction.Transactional
    public @ResponseBody
    String addNewProduct (@RequestBody Product input){
        try {
            input.setInsDate(new Date());
            productRepository.save(input);
            return "Saved";
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }



}
