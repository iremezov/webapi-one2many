package com.example.webapi.controller;

import com.example.webapi.model.Cart;
import com.example.webapi.model.Person;
import com.example.webapi.repository.CartRepository;
import com.example.webapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private PersonRepository personRepository;

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
    public Optional<List<Cart>> getAllProducts(@RequestBody Person input) {

        return cartRepository.findByPerson(input);
    }





}
