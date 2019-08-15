package ru.vvvresearch.otuslibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vvvresearch.otuslibrary.dao.interfaces.BooksDao;
import ru.vvvresearch.otuslibrary.domain.Author;
import ru.vvvresearch.otuslibrary.domain.Book;
import ru.vvvresearch.otuslibrary.domain.Genre;
import ru.vvvresearch.otuslibrary.services.interfaces.AuthorService;
import ru.vvvresearch.otuslibrary.services.interfaces.BookService;
import ru.vvvresearch.otuslibrary.services.interfaces.GenreService;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    private final BooksDao booksDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public BookServiceImpl(BooksDao booksDao, AuthorService authorService, GenreService genreService) {
        this.booksDao = booksDao;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public List<Book> findAllBooksByAuthor(Author author) {
        Integer id = authorService.getIdByAuthor(author);
        return booksDao.getAllByAuthorId(id);
    }

    @Override
    public List<Book> findAllBooksByGenre(Genre genre) {
        Integer id = genreService.getIdByGenre(genre);
        return booksDao.getAllByGenreId(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return booksDao.getAll();
    }

    @Override
    public Book findBookByTitle(String title) {
        return null;
    }

    @Override
    public void addNewBook(Book book) {

    }
}
