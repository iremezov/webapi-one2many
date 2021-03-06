package com.example.webapi.controller;

import com.example.webapi.model.Person;
import com.example.webapi.repository.MsisdnRepository;
import com.example.webapi.repository.PersonRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

    private static final Logger logger
            = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MsisdnRepository msisdnRepository;


    @ApiOperation("Get all available users")
    @GetMapping("/persons")
    public List<Person> getAllUsers() {
        return personRepository.findAll();
    }


    @ApiOperation("Add|modify user")
    @RequestMapping(value = "/addPerson", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody Person addNewPerson (@RequestBody Person input){
        Person n = new Person();
        n.setFirstName(input.getFirstName());
        n.setLastName(input.getLastName());
        n.setEmail(input.getEmail());

        Date date = new Date(System.currentTimeMillis());
        n.setCreatedAt(date);

        if(input.getId() >= 0){
            n.setId(input.getId());
        }

        personRepository.save(n);

        Gson gson = new Gson();
        String json = gson.toJson(input) + gson.toJson(n);
        logger.info("addProduct:" + json);

        return n;
    }

    @ApiOperation("Get user by ID")
    @RequestMapping(value = "/getPersonById", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody String getPersonById(@RequestBody Person input){

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        System.out.println(methodName + "; FirstName=" + input.getFirstName() + "; email=" + input.getEmail());

        Gson gson = new GsonBuilder().create();
        //String jsonObjectArr = gson.toJson(personRepository.findById(input.getId()));
        String jsonObjectArr = gson.toJson(msisdnRepository.findByPersonId(input.getId()));
        System.out.println("GreetingController:::jsonFromGson : "+jsonObjectArr.toString());

        return jsonObjectArr;
    }

    @ApiOperation("Get by user ID it's msisdn list")
    @RequestMapping(value = "/getMsisdnByPersonId", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Person> getMsisdnByPersonId(@RequestBody Person input){
        return new ResponseEntity<Person>(personRepository.findById(input.getId()).get(), HttpStatus.OK);
    }

}