package com.wang;

import com.wang.Controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class RestUserControllerTests {

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

	@Test
	public void contextLoads() throws Exception{

        RequestBuilder requestBuilder = null;


        //1. 测试get
        requestBuilder = get("/users/");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(equalTo("[]")));


        //2. 测试post user
        requestBuilder = post("/users/")
                .param("id", "1")
                .param("age", "12")
                .param("name", "HuangDan");

        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));

        // 3. 重新测试get
        requestBuilder = get("/users/");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());


        //4. put修改
        requestBuilder = put("/users/1/")
                .param("name", "wang");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());


        // 5、del删除id为1的user
        requestBuilder = delete("/users/1");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));

        // 6、get查一下user列表，应该为空
        requestBuilder = get("/users/");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(equalTo("[]")));

    }

}
