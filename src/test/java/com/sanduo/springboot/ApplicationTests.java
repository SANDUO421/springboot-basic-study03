package com.sanduo.springboot;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanduo.springboot.entity.Person;
import com.sanduo.springboot.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ApplicationTests {

    MockMvc mockMvc;// 模拟mvc

    @Autowired
    PersonRepository personRepository;// Repository

    @Autowired
    WebApplicationContext applicationContext;// web应用的context

    String expectedJson;

    // 前置条件
    @Before
    public void setup() throws JsonProcessingException {
        // 构建mockMvc的实例
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
        // 打桩
        Person p1 = new Person("sanduo");
        Person p2 = new Person("lantian");

        personRepository.save(p1);
        personRepository.save(p2);
        expectedJson = Obj2Json(personRepository.findAll());

    }

    /**
     * json格式化
     * 
     * @param findAll
     * @return
     * @throws JsonProcessingException
     */
    private String Obj2Json(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(obj);
    }

    // 测试controller
    @Test
    public void PersonControllerTest() throws Exception {
        String uri = "/person";
        MvcResult mvcResult =
            mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("错误，正确返回200", 200, status);
        Assert.assertEquals("错误，返回和预期不一样", expectedJson, content);
    }

}
