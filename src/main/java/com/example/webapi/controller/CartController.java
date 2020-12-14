package com.example.webapi.controller;

import com.example.webapi.model.*;
import com.example.webapi.repository.CartRepository;
import com.example.webapi.repository.PersonRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
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
    public Optional<List<Cart>> getActiveProducts(@RequestBody Person input) {
        return cartRepository.findByPersonAndState(input.getId(), 1);
    }

    @RequestMapping(value = "/getCartPricev2", method = RequestMethod.POST, headers = "Accept=application/json")
    public Price getCartPriceByCartId2(@RequestBody Price input){

        double sum = 0D;

        Optional<List<Cart>> cartList = cartRepository.findByPersonAndState(input.getInPersonId(), 1);

        for(Cart c: cartList.get()){

            if(c.getId() == input.getInCartId()) {
                for (Product p : c.getProducts()) {
                    sum += p.getPrice();
                }
            }

        }

        input.setPrice(sum);
        return input;
    }

    @RequestMapping(value = "/getCartPrice", method = RequestMethod.POST, headers = "Accept=application/json")
    public Price getCartPriceByCartId(@RequestBody Price input){
        input.setPrice(cartRepository.findSumByCartPerson(input.inCartId, input.inPersonId));
        return input;
    }

    @ApiOperation("Update user cart state (1-саказ в корзине, 2-саказ оплачен, 3-заказ отменен)")
    @RequestMapping(value = "/setCartState", method = RequestMethod.POST, headers = "Accept=application/json")
    public Response setCartState(@RequestBody Cart input){

        Response r = new Response();

        try {

            cartRepository.updateCartStateById(input.getId(), input.getState());

            r.setCode(1);
            r.setMsg("OK");
            return r;

        }catch (Exception e){
            r.setCode(-1);
            r.setMsg(e.toString());
            return r;
        }

    }



}
