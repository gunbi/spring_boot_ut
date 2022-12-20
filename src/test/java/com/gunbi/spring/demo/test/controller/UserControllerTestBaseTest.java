package com.gunbi.spring.demo.test.controller;

import com.gunbi.spring.demo.test.fake.FakeUserBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class UserControllerTestBaseTest extends FakeUserBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_retrieve_one_user() throws Exception {
        this.mockMvc.perform(get("/user/fetch/{id}", 3))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id").value(3))
            .andExpect(jsonPath("$.name").value("Julien Mael"))
            .andExpect(jsonPath("$.email").value("laura.royer@yahoo.fr"));
    }

    @Test
    public void should_create_one_user() throws Exception {
        String userToCreate = "{\"email\": \"akevin@yahoo.fr\",\"name\": \"Arsene Kevin\",\"id\": 6}";

        this.mockMvc.perform(post("/user/create")
                .contentType(APPLICATION_JSON_UTF8)
                .content(userToCreate))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$").isMap());

        this.mockMvc.perform(get("/user/fetch/{id}", 6))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id").value(6));
    }


    @Test
    public void should_retrieve_all_users() throws Exception {
        this.mockMvc.perform(get("/user/fetchAll"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(5)))
            .andExpect(jsonPath("$.[0].id").value(1))
            .andExpect(jsonPath("$.[1].id").value(2))
            .andExpect(jsonPath("$.[2].id").value(3))
            .andExpect(jsonPath("$.[3].id").value(4))
            .andExpect(jsonPath("$.[4].id").value(5));
    }

    @Test
    public void should_delete_one_user() throws Exception {
        this.mockMvc.perform(delete("/user/delete/{id}", 2))
            .andDo(print())
            .andExpect(status().isNoContent());

        this.mockMvc.perform(get("/user/fetch/{id}", 2))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(""));
    }
}