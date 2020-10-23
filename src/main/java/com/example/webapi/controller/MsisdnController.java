package com.example.webapi.controller;

import com.example.webapi.model.Msisdn;
import com.example.webapi.model.Person;
import com.example.webapi.repository.MsisdnRepository;
import com.example.webapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class MsisdnController {

    @Autowired
    private MsisdnRepository msisdnRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    EntityManagerFactory entMng;

    @GetMapping("/msisdns")
    public List<Msisdn> getAllMsisdn() {
        return msisdnRepository.findAll();
    }


    @RequestMapping(value = "/addMsisdn", method = RequestMethod.POST, headers = "Accept=application/json")
    @Transactional
    @javax.transaction.Transactional
    public @ResponseBody
    String addNewPerson (@RequestBody Msisdn input){
        try {
            input.setInsDate(new Date());
            msisdnRepository.save(input);
            return "Saved";
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }


}
