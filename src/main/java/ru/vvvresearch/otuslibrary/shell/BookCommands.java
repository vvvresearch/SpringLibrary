package ru.vvvresearch.otuslibrary.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.vvvresearch.otuslibrary.domain.Author;
import ru.vvvresearch.otuslibrary.domain.Book;
import ru.vvvresearch.otuslibrary.domain.Genre;
import ru.vvvresearch.otuslibrary.services.interfaces.BookService;

import java.util.List;

import static ru.vvvresearch.otuslibrary.shell.CommandHelper.getStringFromList;

@ShellComponent
public class BookCommands {
    private final BookService bookService;

    @Autowired
    public BookCommands(BookService bookService) {
        this.bookService = bookService;
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

    @ShellMethod("Get all books")
    public String books() {
        List<Book> list = bookService.getAllBooks();
        return getStringFromList(list);
    }

}
