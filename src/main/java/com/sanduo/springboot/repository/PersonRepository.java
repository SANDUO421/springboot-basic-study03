package com.sanduo.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanduo.springboot.entity.Person;

/**
 * @author sanduo
 * @date 2018/08/07
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
