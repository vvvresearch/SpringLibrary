package ru.vvvresearch.otuslibrary.services.interfaces;

import org.springframework.stereotype.Service;
import ru.vvvresearch.otuslibrary.domain.Genre;

import java.util.List;

public interface GenreService {
    void addGenre(Genre genre);

    Genre getGenreByName(String name);

    Integer getIdByGenre(Genre genre);

    List<Genre> getAll();
}
