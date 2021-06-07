package com.example.demo.controller;

import com.example.demo.Pss2Application;
import com.example.demo.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
class SiteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void hello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
                content().string(containsString("Hello world!!!"))
        );
    }

    @Test
    void getAllUsers() throws Exception {
       mvc.perform(
               MockMvcRequestBuilders.get("/users"))
               .andExpect(status().isOk());
    }


    @Test
    void getAllUserByRole() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/usersByRole").param("role","pracownik"))
                .andExpect(status().isOk()).andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                ;
    }


    @Test
    void deleteUser() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.delete("/user/delete")
                .param("userId", String.valueOf(0)))
                .andExpect(status().isOk());

    }


    @Test
    void getAllDelegations() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/delegations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void getAllDelegationsOrderByDateStartDesc() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/delegationsOrderByDateDesc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void getAllDelegationsByUserOrderByDateStartDesc() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/delegationsByUserOrderByDateDesc")
                        .param("delegationId", String.valueOf(0)))
                .andExpect(status().isOk());
    }


    @Test
    void deleteDelegation() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.delete("/delegation/delete")
                .param("delegationId","0"))
                .andExpect(status().isOk());
    }
}