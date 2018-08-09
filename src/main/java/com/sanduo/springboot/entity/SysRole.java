package com.sanduo.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统角色
 * 
 * @author sanduo
 * @date 2018/07/31
 */
@ApiModel(description = "系统角色")
@Entity
public class SysRole {

    @ApiModelProperty(value = "角色id")
    @Id
    @GeneratedValue
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
