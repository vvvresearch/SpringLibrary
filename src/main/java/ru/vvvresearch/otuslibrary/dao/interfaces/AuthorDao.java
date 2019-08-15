package ru.vvvresearch.otuslibrary.dao.interfaces;

import org.springframework.stereotype.Repository;
import ru.vvvresearch.otuslibrary.domain.Author;
import ru.vvvresearch.otuslibrary.domain.Book;

import java.util.List;

public interface AuthorDao {
    int count();

    void insert(Author author);

    Author getById(int id);
    Author getByLastAndFirstName(String lastName, String firstName);

    List<Author> getAll();

    void deleteById(int id);


}
