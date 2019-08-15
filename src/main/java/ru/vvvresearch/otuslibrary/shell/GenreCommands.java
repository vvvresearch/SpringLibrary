package ru.vvvresearch.otuslibrary.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.vvvresearch.otuslibrary.dao.AuthorDaoJdbc;
import ru.vvvresearch.otuslibrary.domain.Genre;
import ru.vvvresearch.otuslibrary.services.interfaces.GenreService;

import java.util.List;

import static ru.vvvresearch.otuslibrary.shell.CommandHelper.getStringFromList;

@ShellComponent
public class GenreCommands {
    private GenreService genreService;

    @Autowired
    public GenreCommands(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod("Get all genres")
    public String genres() {
        List<Genre> list = genreService.getAll();
        return getStringFromList(list);
    }

    @ShellMethod("addGenre")
    public String addGenre(@ShellOption String title) {
        genreService.addGenre(Genre.getBuilder().setTitle(title).build());
        return "Genre " + title + " added";
    }
}
