package com.hendisantika.springbootreactivewebapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendisantika.springbootreactivewebapi.dto.request.AddBookRequest;
import com.hendisantika.springbootreactivewebapi.dto.request.AddBookWebRequest;
import com.hendisantika.springbootreactivewebapi.dto.request.UpdateBookRequest;
import com.hendisantika.springbootreactivewebapi.dto.request.UpdateBookWebRequest;
import com.hendisantika.springbootreactivewebapi.dto.response.BookResponse;
import com.hendisantika.springbootreactivewebapi.entity.ErrorCode;
import com.hendisantika.springbootreactivewebapi.service.book.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import rx.Completable;
import rx.Single;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-reactive-web-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-07-02
 * Time: 06:55
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    public void AddBook_Success_Return201() throws Exception {
        when(bookService.addBook(any(AddBookRequest.class)))
                .thenReturn(Single.just("1"));

        MvcResult mvcResult = mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(new AddBookWebRequest())))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.errorCode", nullValue()))
                .andExpect(jsonPath("$.data", nullValue()));

        verify(bookService, times(1)).addBook(any(AddBookRequest.class));
    }

    @Test
    public void AddBook_Failed_AuthorNotFound_Return404EntityNotFound() throws Exception {
        when(bookService.addBook(any(AddBookRequest.class)))
                .thenReturn(Single.error(new EntityNotFoundException()));

        MvcResult mvcResult = mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(new AddBookWebRequest())))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", equalTo(ErrorCode.ENTITY_NOT_FOUND.toString())))
                .andExpect(jsonPath("$.data", nullValue()));

        verify(bookService, times(1)).addBook(any(AddBookRequest.class));
    }

    @Test
    public void UpdateBook_Success_Return200() throws Exception {
        when(bookService.updateBook(any(UpdateBookRequest.class)))
                .thenReturn(Completable.complete());

        MvcResult mvcResult = mockMvc.perform(put("/api/books/id", "123")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(new UpdateBookWebRequest())))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode", nullValue()))
                .andExpect(jsonPath("$.data", nullValue()));

        verify(bookService, times(1)).updateBook(any(UpdateBookRequest.class));
    }

    @Test
    public void UpdateBook_Failed_BookIdNotFound_Return404EntityNotFound() throws Exception {
        when(bookService.updateBook(any(UpdateBookRequest.class)))
                .thenReturn(Completable.error(new EntityNotFoundException()));

        MvcResult mvcResult = mockMvc.perform(put("/api/books/id", "123")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(new UpdateBookWebRequest())))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", equalTo(ErrorCode.ENTITY_NOT_FOUND.toString())))
                .andExpect(jsonPath("$.data", nullValue()));

        verify(bookService, times(1)).updateBook(any(UpdateBookRequest.class));
    }

    @Test
    public void GetAllBooks_LimitAndPageSpecified_Success_Return200WithListOfBookWebResponse() throws Exception {
        when(bookService.getAllBooks(anyInt(), anyInt()))
                .thenReturn(Single.just(Collections.singletonList(new BookResponse("1", "1", "1"))));

        MvcResult mvcResult = mockMvc.perform(get("/api/books?limit=5&page=0")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode", nullValue()))
                .andExpect(jsonPath("$.data[0].id", equalTo("1")));

        verify(bookService, times(1)).getAllBooks(anyInt(), anyInt());
    }


}