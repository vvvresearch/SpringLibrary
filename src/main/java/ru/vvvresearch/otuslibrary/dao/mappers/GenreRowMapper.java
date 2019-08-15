package ru.vvvresearch.otuslibrary.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.vvvresearch.otuslibrary.domain.Genre;

import java.sql.ResultSet;

@Component
public class GenreRowMapper {
    private final RowMapper<Genre> rowMapper = (ResultSet resultSet, int i) -> {
        return new Genre.Builder().setId(resultSet.getInt("genre_id")).setTitle(resultSet.getString("genre_name")).build();
    };

    public RowMapper<Genre> getRowMapper() {
        return rowMapper;
    }

    public GenreRowMapper() {
    }
}