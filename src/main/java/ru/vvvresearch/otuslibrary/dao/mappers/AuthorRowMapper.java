package ru.vvvresearch.otuslibrary.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.vvvresearch.otuslibrary.domain.Author;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
@Component
public class AuthorRowMapper {
    private final RowMapper<Author> rowMapper = (resultSet, i) -> {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(resultSet.getDate("birthdate"));
        String country = resultSet.getString("country_key");
        Locale locale = new Locale(country.toLowerCase(), country);
        return new Author(
                resultSet.getInt("author_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("gender"),
                calendar,
                locale
        );
    };

    public AuthorRowMapper() {
    }

    public RowMapper<Author> getRowMapper() {
        return rowMapper;
    }
}