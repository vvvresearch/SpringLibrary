package ru.vvvresearch.otuslibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.vvvresearch.otuslibrary.dao.interfaces.AuthorDao;
import ru.vvvresearch.otuslibrary.domain.Author;

import java.util.*;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations jdbcNamed;
    private final Map<String, Object> params = new HashMap<>();
    private final RowMapper<Author> rowMapper = (resultSet, i) -> {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(resultSet.getDate("birthdate"));
        String country = resultSet.getString("country_key");
        Locale locale = new Locale(country.toLowerCase(), country);
        return new Author(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("gender"),
                calendar,
                locale
        );
    };

    @Autowired
    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbcNamed) {
        this.jdbcNamed = jdbcNamed;
    }

    @Override
    public int count() {
        return jdbcNamed.queryForObject("select count(*) from authors", params, Integer.class);
    }

    @Override
    public void insert(Author author) {
        params.put("firstName", author.getFirstName());
        params.put("lastName", author.getLastName());
        params.put("gender", author.getGender());
        params.put("birthdate", author.getBirthDate().getTime());
        params.put("locale", author.getLocale().getCountry());
        jdbcNamed.update("insert into authors (first_name,last_name,gender,birthdate,country_key) values (:firstName,:lastName,:gender,:birthdate,:locale)", params);
    }

    @Override
    public Author getById(int id) {
        params.put("authorId", id);
        return jdbcNamed.queryForObject("select * from authors where id = :authorId", params, rowMapper);
    }

    @Override
    public Author getByLastAndFirstName(String lastName, String firstName) {
        params.put("author_lastname", lastName);
        params.put("author_firstname",firstName);
        return jdbcNamed.queryForObject("select * from authors where last_name = :author_lastname and first_name = :author_firstname", params, rowMapper);
    }

    @Override
    public List<Author> getAll() {
        return jdbcNamed.query("select * from authors", rowMapper);
    }

    @Override
    public void deleteById(int id) {
        params.put("authorId", id);
        jdbcNamed.update("delete from authors where id=:authorId", params);

    }
}
