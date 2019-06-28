package com.hendisantika.springbootreactivewebapi.service.author;

import com.hendisantika.springbootreactivewebapi.dto.request.AddAuthorRequest;
import rx.Single;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-reactive-web-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-06-29
 * Time: 06:29
 */
public interface AuthorService {
    Single<String> addAuthor(AddAuthorRequest addAuthorRequest);
}