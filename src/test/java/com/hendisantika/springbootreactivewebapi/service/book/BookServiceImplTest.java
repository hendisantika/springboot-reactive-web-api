package com.hendisantika.springbootreactivewebapi.service.book;

import com.hendisantika.springbootreactivewebapi.repository.AuthorRepository;
import com.hendisantika.springbootreactivewebapi.repository.BookRepository;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-reactive-web-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-06-30
 * Time: 15:05
 */
public class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


}