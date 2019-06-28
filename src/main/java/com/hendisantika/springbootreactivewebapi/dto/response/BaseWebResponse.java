package com.hendisantika.springbootreactivewebapi.dto.response;

import com.hendisantika.springbootreactivewebapi.entity.ErrorCode;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-reactive-web-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-06-29
 * Time: 06:38
 */
@Getter
@Builder
public class BaseWebResponse<T> {
    private ErrorCode errorCode;
    private T data;

    public static BaseWebResponse successNoData() {
        return BaseWebResponse.builder()
                .build();
    }

    public static <T> BaseWebResponse<T> successWithData(T data) {
        return BaseWebResponse.<T>builder()
                .data(data)
                .build();
    }

    public static BaseWebResponse error(ErrorCode errorCode) {
        return BaseWebResponse.builder()
                .errorCode(errorCode)
                .build();
    }
}