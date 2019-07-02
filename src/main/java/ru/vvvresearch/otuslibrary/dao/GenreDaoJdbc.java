package ru.vvvresearch.otuslibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.vvvresearch.otuslibrary.dao.interfaces.GenreDao;
import ru.vvvresearch.otuslibrary.dao.mappers.GenreRowMapper;
import ru.vvvresearch.otuslibrary.domain.Genre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations jdbcNamed;
    private final Map<String, Object> params = new HashMap<>();
    private final GenreRowMapper GenreRowMapper;

    @Autowired
    public GenreDaoJdbc(NamedParameterJdbcOperations jdbcNamed, ru.vvvresearch.otuslibrary.dao.mappers.GenreRowMapper genreRowMapper) {
        this.jdbcNamed = jdbcNamed;
        GenreRowMapper = genreRowMapper;
    }

    @Override
    public int count() {
        return jdbcNamed.queryForObject("select count(*) from genre", params, Integer.class);
    }

    @Override
    public void insert(Genre genre) {
        params.put("title", genre.getTitle());
        jdbcNamed.update("insert into genre (genre_name) values (:title)", params);
    }

    @Override
    public Genre getById(int id) {
        params.put("genreId", id);
        try {
            return jdbcNamed.queryForObject("select * from genre where id = :genreId", params, GenreRowMapper.getRowMapper());
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    @Override
    public Genre getByGenreName(String title) {
        params.put("genreName", title);
        return jdbcNamed.queryForObject("select * from genre where genre_name = :genreName", params, GenreRowMapper.getRowMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbcNamed.query("select * from genre", GenreRowMapper.getRowMapper());
    }

    @Override
    public void deleteById(int id) {
        params.put("genreId", id);
        jdbcNamed.update("delete from genre where id=:genreId", params);

    }
}
