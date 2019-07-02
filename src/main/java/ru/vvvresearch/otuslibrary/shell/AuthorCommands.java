package ru.vvvresearch.otuslibrary.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.vvvresearch.otuslibrary.dao.AuthorDaoJdbc;
import ru.vvvresearch.otuslibrary.domain.Author;
import ru.vvvresearch.otuslibrary.services.interfaces.AuthorService;

import java.util.List;

import static ru.vvvresearch.otuslibrary.shell.CommandHelper.getStringFromList;

@ShellComponent
public class AuthorCommands {

    private AuthorService authorService;

    @Autowired
    public AuthorCommands(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod("Get all authors")
    public String authors() {
        List<Author> list = authorService.getAll();
        return getStringFromList(list);
    }
}
