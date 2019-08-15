package ru.vvvresearch.otuslibrary.dao.interfaces;

import org.springframework.stereotype.Repository;
import ru.vvvresearch.otuslibrary.domain.Book;

import java.util.List;

public interface BooksDao {
    int count();

    void insert(Book book);

    Book getById(int id);

    Book getByTitle(String title);

    List<Book> getAll();

    void deleteById(int id);

    List<Book> getAllByAuthorId(Integer id);

    List<Book> getAllByGenreId(Integer id);

}
