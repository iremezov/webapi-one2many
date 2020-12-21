package com.example.webapi.controller;

import com.example.webapi.model.ProductDetail;
import com.example.webapi.repository.ProductDetailRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product_properties")
public class ProductDetailController {

    private static final Logger logger
            = LoggerFactory.getLogger(ProductDetailController.class);

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @GetMapping("/all")
    public List<ProductDetail> getAllProperties(){
        return productDetailRepository.findAll();
    }

    @RequestMapping(value = "/addProductDetail", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    ProductDetail addNewProductDetail (@RequestBody ProductDetail input){
        try {
            productDetailRepository.save(input);

            Gson gson = new Gson();
            String json = gson.toJson(input);
            logger.info("addProduct:" + json);

            return input;
        }
        catch (Exception e){
            e.printStackTrace();
            input.setId(-1);

            Gson gson = new Gson();
            String json = gson.toJson(input);
            logger.info("addProduct:" + json);

            return input;
        }
    }



}
