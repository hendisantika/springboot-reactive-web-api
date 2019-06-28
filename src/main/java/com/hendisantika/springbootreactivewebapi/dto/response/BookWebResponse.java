package com.hendisantika.springbootreactivewebapi.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-reactive-web-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-06-29
 * Time: 06:39
 */
@Getter
@Setter
@NoArgsConstructor
public class BookWebResponse {
    private String id;
    private String title;
    private String authorName;
}