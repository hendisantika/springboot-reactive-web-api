package com.hendisantika.springbootreactivewebapi.service.book;

import com.hendisantika.springbootreactivewebapi.dto.request.AddBookRequest;
import com.hendisantika.springbootreactivewebapi.entity.Author;
import com.hendisantika.springbootreactivewebapi.entity.Book;
import com.hendisantika.springbootreactivewebapi.repository.AuthorRepository;
import com.hendisantika.springbootreactivewebapi.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

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

    @Test
    public void AddBook_Success_ReturnSingleOfAddedBookId() {
        when(authorRepository.findById(anyString()))
                .thenReturn(Optional.of(new Author("1", "1")));
        when(bookRepository.save(any(Book.class)))
                .thenReturn(new Book("1", "1", new Author()));

        bookService.addBook(new AddBookRequest("1", "1"))
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue("1")
                .awaitTerminalEvent();

        InOrder inOrder = inOrder(authorRepository, bookRepository);
        inOrder.verify(authorRepository, times(1)).findById(anyString());
        inOrder.verify(bookRepository, times(1)).save(any(Book.class));
    }


}