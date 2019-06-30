package com.hendisantika.springbootreactivewebapi.service.author;

import com.hendisantika.springbootreactivewebapi.repository.AuthorRepository;
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
 * Time: 15:00
 */
public class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
}