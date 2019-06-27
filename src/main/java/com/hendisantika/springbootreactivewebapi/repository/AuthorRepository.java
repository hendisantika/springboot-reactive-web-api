package com.hendisantika.springbootreactivewebapi.repository;

import com.hendisantika.springbootreactivewebapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
public interface AuthorRepository extends JpaRepository<Author, String> {
}