package ru.vvvresearch.otuslibrary.services;

import org.springframework.stereotype.Service;
import ru.vvvresearch.otuslibrary.dao.interfaces.AuthorDao;
import ru.vvvresearch.otuslibrary.domain.Author;
import ru.vvvresearch.otuslibrary.services.interfaces.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public void addAuthor(Author author) {
        authorDao.insert(author);
    }

    @Override
    public Author getAuthorByLastAndFirstName(String lastName, String firstName) {
        return authorDao.getByLastAndFirstName(lastName, firstName);
    }

    @Override
    public Integer getIdByAuthor(Author author) {
        return getAuthorByLastAndFirstName(author.getLastName(), author.getFirstName()).getId();
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

}
