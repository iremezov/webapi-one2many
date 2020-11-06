package com.example.webapi.controller;

import com.example.webapi.model.Person;
import com.example.webapi.repository.MsisdnRepository;
import com.example.webapi.repository.PersonRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MsisdnRepository msisdnRepository;


    @GetMapping("/persons")
    public List<Person> getAllUsers() {
        return personRepository.findAll();
    }


    @RequestMapping(value = "/addPerson", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody String addNewPerson (@RequestBody Person input){
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
        return "Saved";
    }

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


    @RequestMapping(value = "/getMsisdnByPersonId", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Person> getMsisdnByPersonId(@RequestBody Person input){
        return new ResponseEntity<Person>(personRepository.findById(input.getId()).get(), HttpStatus.OK);
    }

}