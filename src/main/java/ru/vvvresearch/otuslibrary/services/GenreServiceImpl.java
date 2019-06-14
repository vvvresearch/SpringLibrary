package ru.vvvresearch.otuslibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vvvresearch.otuslibrary.dao.interfaces.GenreDao;
import ru.vvvresearch.otuslibrary.domain.Genre;
import ru.vvvresearch.otuslibrary.services.interfaces.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public void addGenre(Genre genre) {
        genreDao.insert(genre);
    }

    @Override
    public Genre getGenreByName(String name) {
        return genreDao.getByGenreName(name);
    }

    @Override
    public Integer getIdByGenre(Genre genre) {
        return genreDao.getByGenreName(genre.getTitle()).getId();
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
