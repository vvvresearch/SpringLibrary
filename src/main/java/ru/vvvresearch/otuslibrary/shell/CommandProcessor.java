package ru.vvvresearch.otuslibrary.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.vvvresearch.otuslibrary.domain.Author;
import ru.vvvresearch.otuslibrary.domain.Book;
import ru.vvvresearch.otuslibrary.domain.Genre;
import ru.vvvresearch.otuslibrary.services.interfaces.AuthorService;
import ru.vvvresearch.otuslibrary.services.interfaces.BookService;
import ru.vvvresearch.otuslibrary.services.interfaces.GenreService;

import java.util.List;

@ShellComponent
public class CommandProcessor {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public CommandProcessor(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @ShellMethod("Get list books by author name")
    public String booksByAuthor(@ShellOption String first, @ShellOption String last) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Book> list = null;
        list = bookService.findAllBooksByAuthor(Author.getBuilder()
                .setLastName(last)
                .setFirstName(first)
                .build());
        return getStringFromList(list);
    }

    @ShellMethod("Get list books by genre")
    public String booksByGenre(@ShellOption String genre) {
        List<Book> list = bookService.findAllBooksByGenre(Genre.getBuilder()
                .setTitle(genre)
                .build());
        return getStringFromList(list);
    }

    @ShellMethod("Get all genres")
    public String genres() {
        List<Genre> list = genreService.getAll();
        return getStringFromList(list);
    }

    @ShellMethod("Get all authors")
    public String authors() {
        List<Author> list = authorService.getAll();
        return getStringFromList(list);
    }

    @ShellMethod("addGenre")
    public String addGenre(@ShellOption String title) {
        genreService.addGenre(Genre.getBuilder().setTitle(title).build());
        return "Genre " + title + " added";
    }

    private String getStringFromList(List list) {
        StringBuilder stringBuilder = new StringBuilder();
        if (list != null && list.size() > 0) {
            list.forEach(stringBuilder::append);
            return stringBuilder.toString();
        } else {
            return "Not found";
        }
    }


}
