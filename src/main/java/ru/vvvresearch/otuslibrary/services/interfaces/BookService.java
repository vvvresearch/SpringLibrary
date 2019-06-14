package ru.vvvresearch.otuslibrary.services.interfaces;

import org.springframework.stereotype.Service;
import ru.vvvresearch.otuslibrary.domain.Author;
import ru.vvvresearch.otuslibrary.domain.Book;
import ru.vvvresearch.otuslibrary.domain.Genre;

import java.util.List;

public interface BookService {
    List<Book> findAllBooksByAuthor(Author author);

    List<Book> findAllBooksByGenre(Genre genre);

    List<Book> getAllBooks();

    Book findBookByTitle(String title);

    void addNewBook(Book book);
}
