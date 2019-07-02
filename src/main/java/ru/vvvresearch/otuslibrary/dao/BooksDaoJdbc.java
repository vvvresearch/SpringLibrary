package ru.vvvresearch.otuslibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.vvvresearch.otuslibrary.dao.interfaces.BooksDao;
import ru.vvvresearch.otuslibrary.dao.mappers.BookRowMapper;
import ru.vvvresearch.otuslibrary.domain.Book;

import java.util.*;

@Repository
public class BooksDaoJdbc implements BooksDao {
    private final NamedParameterJdbcOperations jdbcNamed;
    private final Map<String, Object> params = new HashMap<>();
    private final BookRowMapper bookRowMapper;

    @Autowired
    public BooksDaoJdbc(NamedParameterJdbcOperations jdbcNamed, BookRowMapper bookRowMapper) {
        this.jdbcNamed = jdbcNamed;
        this.bookRowMapper = bookRowMapper;
    }

    @Override
    public int count() {
        return jdbcNamed.queryForObject("select count(*) from books", params, Integer.class);
    }

    @Override
    public void insert(Book book) {
        params.put("title", book.getTitle());
        params.put("publisher", book.getPublisher());
        params.put("publish_year", book.getPublishYear());
        params.put("author_id", book.getAuthor().getId());
        params.put("genre_id", book.getGenre().getId());
        jdbcNamed.update("insert into books (title,publisher,publish_year,author_id,genre_id) values (:title,:publisher,:publish_year,:author_id,:genre_id)", params);
    }

    @Override
    public Book getById(int id) {
        params.put("book_id", id);
        return jdbcNamed.queryForObject("select b.book_id,b.title,b.publisher,b.publish_year, a.author_id, a.first_name,a.last_name,a.gender, a.birthdate,a.country_key, g.genre_id,g.genre_name from books as b  natural join authors as a natural join genre as g where b.book_id=:book_id;", params, bookRowMapper.getRowMapper());
    }

    @Override
    public Book getByTitle(String title) {
        params.put("book_title", title);
        return jdbcNamed.queryForObject("select b.book_id,b.title,b.publisher,b.publish_year, a.author_id, a.first_name,a.last_name,a.gender, a.birthdate,a.country_key, g.genre_id,g.genre_name from books as b  natural join authors as a  natural join genre as g where b.title=:book_title;", params, bookRowMapper.getRowMapper());
    }

    @Override
    public List<Book> getAll() {

        return jdbcNamed.query("select b.book_id,b.title,b.publisher,b.publish_year, a.author_id, a.first_name,a.last_name,a.gender, a.birthdate,a.country_key, g.genre_id,g.genre_name from books as b  natural join authors as a natural join genre as g;", params, bookRowMapper.getRowMapper());
    }

    @Override
    public void deleteById(int id) {
        params.put("book_id", id);
        jdbcNamed.update("delete from books where id=:book_id", params);
    }

    @Override
    public List<Book> getAllByAuthorId(Integer id) {
        params.put("author_id", id);
        return jdbcNamed.query("select b.book_id,b.title,b.publisher,b.publish_year, a.author_id, a.first_name,a.last_name,a.gender, a.birthdate,a.country_key, g.genre_id,g.genre_name from books as b  inner join authors as a on b.author_id=a.id inner join genre as g on b.genre_id=g.id where a.id=:author_id;", params, bookRowMapper.getRowMapper());
    }

    @Override
    public List<Book> getAllByGenreId(Integer id) {
        params.put("genre_id", id);
        return jdbcNamed.query("select b.book_id,b.title,b.publisher,b.publish_year, a.author_id, a.first_name,a.last_name,a.gender, a.birthdate,a.country_key, g.genre_id,g.genre_name from books as b  natural join authors as a natural join genre as g where g.id=:genre_id;", params, bookRowMapper.getRowMapper());
    }
}
