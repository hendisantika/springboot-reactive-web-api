package com.hendisantika.springbootreactivewebapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendisantika.springbootreactivewebapi.dto.request.AddAuthorRequest;
import com.hendisantika.springbootreactivewebapi.dto.request.AddAuthorWebRequest;
import com.hendisantika.springbootreactivewebapi.service.author.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import rx.Single;

import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-reactive-web-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-07-02
 * Time: 06:52
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = AuthorRestController.class)
public class AuthorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthorService authorService;

    @Test
    public void AddAuthor_Success_Return201() throws Exception {
        when(authorService.addAuthor(any(AddAuthorRequest.class)))
                .thenReturn(Single.just("1"));

        MvcResult mvcResult = mockMvc.perform(post("/api/authors")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(new AddAuthorWebRequest("1"))))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.errorCode", nullValue()))
                .andExpect(jsonPath("$.data", nullValue()));

        verify(authorService, times(1)).addAuthor(any(AddAuthorRequest.class));
    }

}