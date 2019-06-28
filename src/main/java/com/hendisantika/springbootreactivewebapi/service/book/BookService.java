package com.hendisantika.springbootreactivewebapi.service.book;

import com.hendisantika.springbootreactivewebapi.dto.request.AddBookRequest;
import com.hendisantika.springbootreactivewebapi.dto.request.UpdateBookRequest;
import com.hendisantika.springbootreactivewebapi.dto.response.BookResponse;
import rx.Completable;
import rx.Single;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-reactive-web-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-06-29
 * Time: 06:30
 */
public interface BookService {
    Single<String> addBook(AddBookRequest addBookRequest);

    Completable updateBook(UpdateBookRequest updateBookRequest);

    Single<List<BookResponse>> getAllBooks(int limit, int page);

    Single<BookResponse> getBookDetail(String id);

    Completable deleteBook(String id);
}