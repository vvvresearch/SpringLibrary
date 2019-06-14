package ru.vvvresearch.otuslibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.vvvresearch.otuslibrary.dao.interfaces.BooksDao;
import ru.vvvresearch.otuslibrary.domain.Author;
import ru.vvvresearch.otuslibrary.domain.Book;
import ru.vvvresearch.otuslibrary.domain.Genre;

import java.util.*;

@Repository
public class BooksDaoJdbc implements BooksDao {
    private final NamedParameterJdbcOperations jdbcNamed;
    private final Map<String, Object> params = new HashMap<>();
    private final RowMapper<Book> rowMapper = (resultSet, i) -> {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(resultSet.getDate("birthdate"));
        Author author = Author.getBuilder()
                .setId(resultSet.getInt("a_id"))
                .setLastName(resultSet.getString("last_name"))
                .setFirstName(resultSet.getString("first_name"))
                .setBirthDate(calendar)
                .setGender(resultSet.getString("gender"))
                .setLocale(new Locale(resultSet.getString("country_key").toLowerCase(), resultSet.getString("country_key")))
                .build();
        Genre genre = Genre.getBuilder()
                .setId(resultSet.getInt("g_id"))
                .setTitle(resultSet.getString("genre_name"))
                .build();
        Book book = Book.getBuilder()
                .setId(resultSet.getInt("id"))
                .setTitle(resultSet.getString("title"))
                .setPublisher(resultSet.getString("publisher"))
                .setPublishYear(resultSet.getString("publish_year"))
                .setAuthor(author)
                .setGenre(genre)
                .build();
        return book;
    };

    @Autowired
    public BooksDaoJdbc(NamedParameterJdbcOperations jdbcNamed) {
        this.jdbcNamed = jdbcNamed;
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
        return jdbcNamed.queryForObject("select b.id,b.title,b.publisher,b.publish_year, a.id as a_id, a.first_name,a.last_name,a.gender, a.birthdate,a.country_key, g.id as g_id,g.genre_name from books as b  inner join authors as a on b.author_id=a.id inner join genre as g on b.genre_id=g.id where b.id=:book_id;", params, rowMapper);
    }

    @Override
    public Book getByTitle(String title) {
        params.put("book_title", title);
        return jdbcNamed.queryForObject("select b.id,b.title,b.publisher,b.publish_year, a.id as a_id, a.first_name,a.last_name,a.gender, a.birthdate,a.country_key, g.id as g_id,g.genre_name from books as b  inner join authors as a on b.author_id=a.id inner join genre as g on b.genre_id=g.id where b.title=:book_title;", params, rowMapper);
    }

    @Override
    public List<Book> getAll() {

        return jdbcNamed.query("select b.id,b.title,b.publisher,b.publish_year, a.id as a_id, a.first_name,a.last_name,a.gender, a.birthdate,a.country_key, g.id as g_id,g.genre_name from books as b  inner join authors as a on b.author_id=a.id inner join genre as g on b.genre_id=g.id;", params, rowMapper);
    }

    @Override
    public void deleteById(int id) {
        params.put("book_id", id);
        jdbcNamed.update("delete from books where id=:book_id", params);
    }

    @Override
    public List<Book> getAllByAuthorId(Integer id) {
        params.put("author_id", id);
        return jdbcNamed.query("select b.id,b.title,b.publisher,b.publish_year, a.id as a_id, a.first_name,a.last_name,a.gender, a.birthdate,a.country_key, g.id as g_id,g.genre_name from books as b  inner join authors as a on b.author_id=a.id inner join genre as g on b.genre_id=g.id where a.id=:author_id;", params, rowMapper);
    }

    @Override
    public List<Book> getAllByGenreId(Integer id) {
        params.put("genre_id", id);
        return jdbcNamed.query("select b.id,b.title,b.publisher,b.publish_year, a.id as a_id, a.first_name,a.last_name,a.gender, a.birthdate,a.country_key, g.id as g_id,g.genre_name from books as b  inner join authors as a on b.author_id=a.id inner join genre as g on b.genre_id=g.id where g.id=:genre_id;", params, rowMapper);
    }
}
