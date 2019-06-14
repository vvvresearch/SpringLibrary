package ru.vvvresearch.otuslibrary.dao.interfaces;

import org.springframework.stereotype.Repository;
import ru.vvvresearch.otuslibrary.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();

    void insert(Genre book);

    Genre getById(int id);
    Genre getByGenreName(String title);

    List<Genre> getAll();

    void deleteById(int id);


}
