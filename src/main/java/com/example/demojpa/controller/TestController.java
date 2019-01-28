package com.example.demojpa.controller;

import com.example.demojpa.entity.AddressEntity;
import com.example.demojpa.entity.UserEntity;
import com.example.demojpa.repository.AddressRepository;
import com.example.demojpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is example for manage entity
 * Spring jpa away put the entity in to persistenceContext when the
 * entity created from repo... ex[save,findBy etc.],
 * so if we modify entity and calling some other save transaction
 * system will also save that entity without call repo.save....
 * The way to avoid this issue we need to call detach on that entity by
 * using EntityManager
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/{param}")
    public void test(@PathVariable("param") String txt){

        //Entity created by jpa and it on track...
        UserEntity userEntityTest = repository.findByUsername("rkrtichat");
        userEntityTest.setPassword(txt);

        /**
         * This is logic for remove the entity from persistenceContext
         */
        //check entity system will return true because userEntityTest create by Jpa
        System.out.println(entityManager.contains(userEntityTest));
        //remove Entity userEntityTest from persistenceContext
        entityManager.detach(userEntityTest);
        //re-check again system will return false
        System.out.println(entityManager.contains(userEntityTest));

        //other save transaction
        //If we not call entityManager.detach, system will save entity of userEntityTest also...
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity("Test");
        addressRepository.save(addressEntity);
    }
}
