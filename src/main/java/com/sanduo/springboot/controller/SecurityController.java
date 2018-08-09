package com.sanduo.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanduo.springboot.entity.Msg;

import io.swagger.annotations.Api;

/**
 * 验证Security
 * 
 * @author sanduo
 * @date 2018/07/31
 */
@Api(description = "验证security")
@Controller
public class SecurityController {
    /**
     * springboot + security + thymeleaf
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/")
    public String index(Model model) {
        // FIXME springboot + security + thymeleaf:次功能暂时不能使用
        Msg msg = new Msg();
        msg.setTitle("测是标题");
        msg.setContent("测是内容");
        msg.setExtraInfo("额外信息");
        msg.setDescription("细节描述");
        model.addAttribute("msg", msg);
        return "home";

    }

}
