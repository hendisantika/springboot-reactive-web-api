package com.hendisantika.springbootreactivewebapi.dto.request;

import lombok.AllArgsConstructor;
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
 * Time: 06:36
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddAuthorWebRequest {
    private String name;
}