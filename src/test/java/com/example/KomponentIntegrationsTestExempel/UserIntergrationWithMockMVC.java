package com.example.KomponentIntegrationsTestExempel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class UserIntergrationWithMockMVC {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    //Funkar inte importer är nog fel
    void testCreateAndGetUser() throws Exception {
          //Arrange
        User user = new User(null,"test","test@");

        //Act and assert
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("test")))
        .andExpect(jsonPath("$.email", is("test@")));

        //act
        User savedUser = userRepository.findAll().get(0);

        mockMvc.perform(get("/user/"+savedUser.getId())).
                andExpect(status().isOk()).andExpect(jsonPath("$.name",is("test"))).
                andExpect(jsonPath("$.email", is("test@")));

    }



}
