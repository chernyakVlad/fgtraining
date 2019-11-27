package com.training.SpringBootTask.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.SpringBootTask.controllers.AuthenticationController;
import com.training.SpringBootTask.controllers.UserController;
import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.models.authentication.LoginUser;
import com.training.SpringBootTask.services.UserService;
import com.training.SpringBootTask.services.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationContollerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void loginUserTest() throws Exception {
        LoginUser user = new LoginUser("test4", "123456");

        mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void loginUserWithInvalidLoginOrPasswordTest() throws Exception {
        LoginUser user = new LoginUser("test4", "123456789");

        mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void requestWithExpiredToken() throws Exception {

        mvc.perform(get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0NCIsInNjb3BlcyI6IlJPTEVfVVNFUiIsImlhdCI6MTU3NDc3MDczMCwiZXhwIjoxNTc0NzcyNTMwfQ.enFzRLMit4ArY-xXXao_KCE8btYmvpNksrjzhE_0XGc"))
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}