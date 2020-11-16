package com.example.proov.tasks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class SampleTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() throws Exception{
        mockMvc.perform(get("/selectmultiplecustomers").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].name").value("Ott"));
//                .andExpect(content().string(containsString("Ott")));
    }
}
