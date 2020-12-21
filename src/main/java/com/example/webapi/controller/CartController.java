package com.example.webapi.controller;

import com.example.webapi.model.*;
import com.example.webapi.repository.CartRepository;
import com.example.webapi.repository.PersonRepository;
import com.example.webapi.repository.ProductDetailRepository;
import com.example.webapi.repository.ProductRepository;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger
            = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/addCart", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    Cart addNewCart (@RequestBody Cart input){
        try {
            input.setInsDate(new Date());
            input.setState(0);

            //recount amounts of goods
            for (Product i : input.getProducts()){
                Optional<Product> p = productRepository.findById(i.getId());
                Integer totalCount = p.get().getProductDetails().getTotalCount();
                Integer reservedCount = p.get().getProductDetails().getReserved();
                p.get().getProductDetails().setReserved(reservedCount + 1);
                p.get().getProductDetails().setTotalCount(totalCount - 1);
            }

            Gson gson = new Gson();
            String json = gson.toJson(input);
            logger.info("addProduct:" + json);

            cartRepository.save(input);
            return input;
        }
        catch (Exception e){
            e.printStackTrace();
            input.setState(-1);

            Gson gson = new Gson();
            String json = gson.toJson(input);
            logger.info("addProduct:" + json);

            return input;
        }
    }


    @RequestMapping(value = "/getCartByUserId", method = RequestMethod.POST, headers = "Accept=application/json")
    public Optional<List<Cart>> getActiveProducts(@RequestBody Person input) {

        Gson gson = new Gson();
        String json = gson.toJson(input);
        logger.info("addProduct:" + json);

        return cartRepository.findByPersonAndState(input.getId(), 0);
    }

    @RequestMapping(value = "/getCartPricev2", method = RequestMethod.POST, headers = "Accept=application/json")
    public Price getCartPriceByCartId2(@RequestBody Price input){

        double sum = 0D;

        Optional<List<Cart>> cartList = cartRepository.findByPersonAndState(input.getInPersonId(), 0);

        for(Cart c: cartList.get()){

            if(c.getId() == input.getInCartId()) {
                for (Product p : c.getProducts()) {
                    sum += p.getPrice();
                }
            }

        }

        input.setPrice(sum);

        Gson gson = new Gson();
        String json = gson.toJson(input);
        logger.info("addProduct:" + json);

        return input;
    }

    @RequestMapping(value = "/getCartPrice", method = RequestMethod.POST, headers = "Accept=application/json")
    public Price getCartPriceByCartId(@RequestBody Price input){
        input.setPrice(cartRepository.findSumByCartPerson(input.inCartId, input.inPersonId));

        Gson gson = new Gson();
        String json = gson.toJson(input);
        logger.info("addProduct:" + json);

        return input;
    }

    @ApiOperation("Update user cart state (0-саказ в корзине, 1-саказ оплачен, 2-заказ отменен)")
    @RequestMapping(value = "/setCartState", method = RequestMethod.POST, headers = "Accept=application/json")
    public Response setCartState(@RequestBody Cart input){

        Response r = new Response();

        try {

            cartRepository.updateCartStateById(input.getId(), input.getState());

            r.setCode(1);
            r.setMsg("OK");

            Gson gson = new Gson();
            String json = gson.toJson(input) + gson.toJson(r);
            logger.info("addProduct:" + json);

            return r;

        }catch (Exception e){
            r.setCode(-1);
            r.setMsg(e.toString());

            Gson gson = new Gson();
            String json = gson.toJson(input) + gson.toJson(r);
            logger.info("addProduct:" + json);

            return r;
        }

    }

    @RequestMapping(value = "/makePayment", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    Response makePayment (@RequestBody Cart input) {

        Response r = new Response();

        try{

            Optional<Cart> c = cartRepository.findById(input.getId());

            if(c.get().getState() == 0){

                c.get().setState(1);
                for (Product p : c.get().getProducts()) {

                    Integer reservedCount = p.getProductDetails().getReserved();
                    ProductDetail productDetail = p.getProductDetails();
                    productDetail.setReserved(reservedCount - 1);
                    productDetailRepository.save(productDetail);
                    System.out.println("ProductId:" + p.getId() + "|ProductName:" + p.getName() + "|" + reservedCount);
                }

            }else{
                r.setCode(-1);
                r.setMsg("Already payed or canceled");

                Gson gson = new Gson();
                String json = gson.toJson(input) + gson.toJson(r);
                logger.info("addProduct:" + json);

                return r;
            }

            r.setCode(1);
            r.setMsg("OK");

            Gson gson = new Gson();
            String json = gson.toJson(input) + gson.toJson(r);
            logger.info("addProduct:" + json);

            return r;

        }catch (Exception e){
            r.setCode(-1);
            r.setMsg(e.toString());

            Gson gson = new Gson();
            String json = gson.toJson(input) + gson.toJson(r);
            logger.info("addProduct:" + json);

            return r;
        }

    }

    @RequestMapping(value = "/cancelPayment", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    Response cancelPayment (@RequestBody Cart input) {
        Response r = new Response();

        try{

            Optional<Cart> c = cartRepository.findById(input.getId());

            if(c.get().getState() == 0){

                c.get().setState(2);

                for (Product p : c.get().getProducts()) {
                    Integer reservedCount = p.getProductDetails().getReserved();
                    Integer totalCount = p.getProductDetails().getTotalCount();

                    ProductDetail productDetail = p.getProductDetails();
                    productDetail.setReserved(reservedCount - 1);
                    productDetail.setTotalCount(totalCount + 1);
                    productDetailRepository.save(productDetail);
                    System.out.println("ProductId:" + p.getId() + "|ProductName:" + p.getName() + "|" + reservedCount + "|" + totalCount);
                }

            }else{
                r.setCode(-1);
                r.setMsg("Already payed or canceled");

                Gson gson = new Gson();
                String json = gson.toJson(input) + gson.toJson(r);
                logger.info("addProduct:" + json);

                return r;
            }

            r.setCode(1);
            r.setMsg("OK");

            Gson gson = new Gson();
            String json = gson.toJson(input) + gson.toJson(r);
            logger.info("addProduct:" + json);

            return r;
        }catch (Exception e){
            r.setCode(-1);
            r.setMsg(e.toString());

            Gson gson = new Gson();
            String json = gson.toJson(input) + gson.toJson(r);
            logger.info("addProduct:" + json);

            return r;
        }
    }




}
