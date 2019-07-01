package com.hendisantika.springbootreactivewebapi.controller;

import com.hendisantika.springbootreactivewebapi.dto.request.AddBookRequest;
import com.hendisantika.springbootreactivewebapi.dto.request.AddBookWebRequest;
import com.hendisantika.springbootreactivewebapi.dto.response.BaseWebResponse;
import com.hendisantika.springbootreactivewebapi.service.book.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Single;
import rx.schedulers.Schedulers;

import java.net.URI;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-reactive-web-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-07-01
 * Time: 19:32
 */
@RestController
@RequestMapping(value = "/api/books")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<BaseWebResponse>> addBook(@RequestBody AddBookWebRequest addBookWebRequest) {
        return bookService.addBook(toAddBookRequest(addBookWebRequest))
                .subscribeOn(Schedulers.io())
                .map(s -> ResponseEntity.created(URI.create("/api/books/" + s)).body(BaseWebResponse.successNoData()));
    }

    private AddBookRequest toAddBookRequest(AddBookWebRequest addBookWebRequest) {
        AddBookRequest addBookRequest = new AddBookRequest();
        BeanUtils.copyProperties(addBookWebRequest, addBookRequest);
        return addBookRequest;
    }
}
