package ru.vvvresearch.otuslibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.vvvresearch.otuslibrary.dao.interfaces.AuthorDao;
import ru.vvvresearch.otuslibrary.dao.mappers.AuthorRowMapper;
import ru.vvvresearch.otuslibrary.domain.Author;

import java.util.*;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations jdbcNamed;
    private final Map<String, Object> params = new HashMap<>();
    private final AuthorRowMapper authorRowMapper;

    @Autowired
    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbcNamed, AuthorRowMapper authorRowMapper) {
        this.jdbcNamed = jdbcNamed;
        this.authorRowMapper = authorRowMapper;
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
        return jdbcNamed.queryForObject("select * from authors where author_id = :authorId", params, authorRowMapper.getRowMapper());
    }

    @Override
    public Author getByLastAndFirstName(String lastName, String firstName) {
        params.put("author_lastname", lastName);
        params.put("author_firstname",firstName);
        return jdbcNamed.queryForObject("select * from authors where last_name = :author_lastname and first_name = :author_firstname", params, authorRowMapper.getRowMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbcNamed.query("select * from authors", authorRowMapper.getRowMapper());
    }

    @Override
    public void deleteById(int id) {
        params.put("authorId", id);
        jdbcNamed.update("delete from authors where author_id=:authorId", params);

    }
}
