package ru.vvvresearch.otuslibrary.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.vvvresearch.otuslibrary.dao.interfaces.AuthorDao;
import ru.vvvresearch.otuslibrary.dao.interfaces.BooksDao;
import ru.vvvresearch.otuslibrary.dao.interfaces.GenreDao;
import ru.vvvresearch.otuslibrary.domain.Author;
import ru.vvvresearch.otuslibrary.domain.Book;
import ru.vvvresearch.otuslibrary.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class BooksDaoJdbcTest {
    @Autowired
    BooksDao booksDao;

    @Autowired
    AuthorDao authorDao;

    @Autowired
    GenreDao genreDao;

    @Test
    void count() {
        assertTrue(booksDao.count() > 0);
    }

    @Test
    void insert() {
        Author author = authorDao.getById(1);
        Genre genre = genreDao.getById(1);
        Book book = new Book.Builder()
                .setId(null)
                .setTitle("Что-то с чем-то")
                .setPublisher("АрмэнИздат")
                .setPublishYear("2019")
                .setAuthor(author)
                .setGenre(genre)
                .build();
        booksDao.insert(book);
        Book bookLoad = booksDao.getByTitle("Что-то с чем-то");
        assertEquals(bookLoad, book);
    }

    @Test
    void getById() {
        Book bookLoad = booksDao.getByTitle("Евгений Онегин");
        Book bookByID = booksDao.getById(bookLoad.getId());
        assertEquals(bookLoad, bookByID);
    }

    @Test
    void getAll() {
        List<Book> books = booksDao.getAll();
        assertNotNull(books);
        assertTrue(books.size()>0);
    }

    @Test
    void deleteById() {
        Author author = authorDao.getById(1);
        Genre genre = genreDao.getById(1);
        Book book = new Book.Builder().setId(null).setTitle("Что-то с чем-то").setPublisher("АрмэнИздат").setPublishYear("2019").setAuthor(author).setGenre(genre).build();
        booksDao.insert(book);
        Book bookLoad = booksDao.getByTitle("Что-то с чем-то");
        booksDao.deleteById(bookLoad.getId());
        assertThrows(Exception.class,()->booksDao.getByTitle("Что-то с чем-то"));
    }
}