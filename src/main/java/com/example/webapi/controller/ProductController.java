package com.example.webapi.controller;

import com.example.webapi.model.Msisdn;
import com.example.webapi.model.Person;
import com.example.webapi.model.Product;
import com.example.webapi.model.ProductDetail;
import com.example.webapi.repository.ProductDetailRepository;
import com.example.webapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.IntToLongFunction;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @GetMapping("/products")
    public List<Product> getActiveProducts() {
        return productRepository.findActiveProducts(1);
    }

    @GetMapping("/getallproducts")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody Product addNewProduct (@RequestBody Product input){
        try {
            input.setInsDate(new Date());
            productRepository.save(input);

            ProductDetail pd = new ProductDetail();
            pd.setReserved(0);
            pd.setTotalCount(0);
            pd.setProduct(input);
            productDetailRepository.save(pd);

            return input;
        }
        catch (Exception e){
            e.printStackTrace();
            input.setId(-1);
            input.setDescription("Error");
            return input;
        }
    }

    //@GetMapping("/getProductByCatalogId")
    //@PostMapping(path = "/getProductByCatalogId", consumes = "application/json", produces = "application/json")
    @RequestMapping(value = "/getProductByCatalogId", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<Product> getProductByCatalogId (@RequestBody Map<String, String> allParams){
        return productRepository.findActiveProductsByCatalog(Integer.parseInt(allParams.get("statusId")), Integer.parseInt(allParams.get("catalogId")));
    }



}
