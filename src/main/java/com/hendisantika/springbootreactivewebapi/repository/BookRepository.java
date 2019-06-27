package com.hendisantika.springbootreactivewebapi.repository;

import com.hendisantika.springbootreactivewebapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-reactive-web-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-06-28
 * Time: 06:23
 */
@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findAllByAuthorId(String authorId);
}