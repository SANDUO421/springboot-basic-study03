package com.sanduo.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanduo.springboot.entity.SysUser;

/**
 * @author sanduo
 * @date 2018/07/31
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    SysUser findByUsername(String username);
}
