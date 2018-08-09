package com.sanduo.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sanduo.springboot.entity.Person;
import com.sanduo.springboot.repository.PersonRepository;

import io.swagger.annotations.Api;

/**
 * @author sanduo
 * @date 2018/08/07
 */
@Api(description = "测试springbootTest")
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Person> findAll() {

        return personRepository.findAll();
    }

}
