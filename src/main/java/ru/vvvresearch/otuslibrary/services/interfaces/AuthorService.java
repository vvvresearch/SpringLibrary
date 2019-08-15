package ru.vvvresearch.otuslibrary.services.interfaces;

import ru.vvvresearch.otuslibrary.domain.Author;

import java.util.List;

public interface AuthorService {
    void addAuthor(Author author);

    Author getAuthorByLastAndFirstName(String lastName, String firstName);
    Integer getIdByAuthor(Author author);

    List<Author> getAll();
}
