package ru.vvvresearch.otuslibrary.dao.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.vvvresearch.otuslibrary.domain.Author;
import ru.vvvresearch.otuslibrary.domain.Book;
import ru.vvvresearch.otuslibrary.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
@Component
public class BookRowMapper {
    private final AuthorRowMapper authorRowMapper;
    private final GenreRowMapper genreRowMapper;
    private final RowMapper<Book> rowMapper = this::mapRow;

    public RowMapper<Book> getRowMapper() {
        return rowMapper;
    }
    @Autowired
    public BookRowMapper(AuthorRowMapper authorRowMapper, GenreRowMapper genreRowMapper) {
        this.authorRowMapper = authorRowMapper;
        this.genreRowMapper = genreRowMapper;
    }

    private Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(resultSet.getDate("birthdate"));
        Author author = authorRowMapper.getRowMapper().mapRow(resultSet, i);
        Genre genre = genreRowMapper.getRowMapper().mapRow(resultSet, i);
        Book book = Book.getBuilder()
                .setId(resultSet.getInt("book_id"))
                .setTitle(resultSet.getString("title"))
                .setPublisher(resultSet.getString("publisher"))
                .setPublishYear(resultSet.getString("publish_year"))
                .setAuthor(author)
                .setGenre(genre)
                .build();
        return book;
    }
}