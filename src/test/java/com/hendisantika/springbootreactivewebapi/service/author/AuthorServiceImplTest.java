package com.hendisantika.springbootreactivewebapi.service.author;

import com.hendisantika.springbootreactivewebapi.dto.request.AddAuthorRequest;
import com.hendisantika.springbootreactivewebapi.entity.Author;
import com.hendisantika.springbootreactivewebapi.repository.AuthorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    public void AddAuthor_Success_ReturnSingleOfAddedAuthorId() {
        when(authorRepository.save(any(Author.class)))
                .thenReturn(new Author("1", "Axell"));

        authorService.addAuthor(new AddAuthorRequest("1"))
                .test()
                .assertComplete()
                .assertNoErrors()
                .awaitTerminalEvent();

        verify(authorRepository, times(1)).save(any(Author.class));
    }
}