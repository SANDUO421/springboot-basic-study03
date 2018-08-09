package com.sanduo.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sanduo.springboot.entity.SysUser;
import com.sanduo.springboot.repository.SysUserRepository;

/**
 * @author sanduo
 * @date 2018/07/31
 */
public class CustomerService implements UserDetailsService {

    @Autowired
    SysUserRepository sysUserRepository;

    /* 获得用户
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;// 当前用户实现了UserDetails可以直接返回
    }

}
