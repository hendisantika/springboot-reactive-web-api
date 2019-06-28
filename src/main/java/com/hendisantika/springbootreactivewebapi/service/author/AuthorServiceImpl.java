package com.hendisantika.springbootreactivewebapi.service.author;

import com.hendisantika.springbootreactivewebapi.dto.request.AddAuthorRequest;
import com.hendisantika.springbootreactivewebapi.entity.Author;
import com.hendisantika.springbootreactivewebapi.repository.AuthorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-reactive-web-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-06-29
 * Time: 06:30
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Single<String> addAuthor(AddAuthorRequest addAuthorRequest) {
        return addAuthorToRepository(addAuthorRequest);
    }

    private Single<String> addAuthorToRepository(AddAuthorRequest addAuthorRequest) {
        return Single.create(singleSubscriber -> {
            String addedAuthorId = authorRepository.save(toAuthor(addAuthorRequest)).getId();
            singleSubscriber.onSuccess(addedAuthorId);
        });
    }

    private Author toAuthor(AddAuthorRequest addAuthorRequest) {
        Author author = new Author();
        BeanUtils.copyProperties(addAuthorRequest, author);
        author.setId(UUID.randomUUID().toString());
        return author;
    }
}