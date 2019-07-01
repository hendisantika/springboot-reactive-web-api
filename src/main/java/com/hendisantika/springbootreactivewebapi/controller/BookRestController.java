package com.hendisantika.springbootreactivewebapi.controller;

import com.hendisantika.springbootreactivewebapi.dto.request.AddBookRequest;
import com.hendisantika.springbootreactivewebapi.dto.request.AddBookWebRequest;
import com.hendisantika.springbootreactivewebapi.dto.request.UpdateBookRequest;
import com.hendisantika.springbootreactivewebapi.dto.request.UpdateBookWebRequest;
import com.hendisantika.springbootreactivewebapi.dto.response.BaseWebResponse;
import com.hendisantika.springbootreactivewebapi.service.book.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping(
            value = "/{bookId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<BaseWebResponse>> updateBook(@PathVariable(value = "bookId") String bookId,
                                                              @RequestBody UpdateBookWebRequest updateBookWebRequest) {
        return bookService.updateBook(toUpdateBookRequest(bookId, updateBookWebRequest))
                .subscribeOn(Schedulers.io())
                .toSingle(() -> ResponseEntity.ok(BaseWebResponse.successNoData()));
    }

    private UpdateBookRequest toUpdateBookRequest(String bookId, UpdateBookWebRequest updateBookWebRequest) {
        UpdateBookRequest updateBookRequest = new UpdateBookRequest();
        BeanUtils.copyProperties(updateBookWebRequest, updateBookRequest);
        updateBookRequest.setId(bookId);
        return updateBookRequest;
    }
}
