package com.hendisantika.springbootreactivewebapi.dto;

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
 * Date: 2019-06-28
 * Time: 06:26
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddAuthorRequest {
    private String name;
}