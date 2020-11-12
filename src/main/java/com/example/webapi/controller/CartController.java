package com.example.webapi.controller;

import com.example.webapi.model.Cart;
import com.example.webapi.model.Product;
import com.example.webapi.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @RequestMapping(value = "/addCart", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    Cart addNewCart (@RequestBody Cart input){
        try {
            input.setInsDate(new Date());

            if(input.getId() > 0){
                input.setState(input.getState());
            }else{
                input.setState(1);
            }

            cartRepository.save(input);
            return input;
        }
        catch (Exception e){
            e.printStackTrace();
            input.setState(-1);
            return input;
        }
    }


    @RequestMapping(value = "/getCartByUserId", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<Cart> getAllProducts(@RequestBody Cart input) {
        return cartRepository.findByUserId(input.getUserId());
    }

/*
    @RequestMapping(value = "/getCartByUserIdv1", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Cart> getCartByUserIdv(@RequestBody Cart input){
        return new ResponseEntity<Cart>(cartRepository.findById(input.getProductId()).get(), HttpStatus.OK);
    }
*/



}
