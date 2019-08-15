package ru.vvvresearch.otuslibrary.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.vvvresearch.otuslibrary.dao.interfaces.AuthorDao;
import ru.vvvresearch.otuslibrary.domain.Author;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class AuthorDaoJdbcTest {
    @Autowired
    AuthorDao authorDao;

    @Test
    void count() {
        assertTrue(authorDao.count() > 0);
    }

    @Test
    void insert() {
        Author author = new Author(null, "Николай Гаврилович", "Чернышевский", "М", new GregorianCalendar(1828, 6, 24), Locale.forLanguageTag("ru-RU"));
        authorDao.insert(author);
        Author authorRead = authorDao.getByLastAndFirstName("Чернышевский", "Николай Гаврилович");
        assertEquals(author, authorRead);
    }

    @Test
    void getById() {
        Author author = new Author(null, "Николай Гаврилович", "Чернышевский", "М", new GregorianCalendar(1828, 6, 24), Locale.forLanguageTag("ru-RU"));
        authorDao.insert(author);
        Author byLastAndFirstName = authorDao.getByLastAndFirstName("Чернышевский", "Николай Гаврилович");
        Author byId = authorDao.getById(byLastAndFirstName.getId());
        assertEquals(byId, byLastAndFirstName);
    }

    @Test
    void getAll() {
        Author author = new Author(null, "Николай Гаврилович", "Чернышевский", "М", new GregorianCalendar(1828, 6, 24), Locale.forLanguageTag("ru-RU"));
        authorDao.insert(author);
        List<Author> authorList = authorDao.getAll();
        assertNotNull(authorList);
        assertTrue(authorList.size() > 0);
    }

    @Test
    void deleteById() {
        Author author = new Author(null, "Николай Гаврилович", "Чернышевский", "М", new GregorianCalendar(1828, 6, 24), Locale.forLanguageTag("ru-RU"));
        authorDao.insert(author);
        Author byLastAndFirstName = authorDao.getByLastAndFirstName("Чернышевский", "Николай Гаврилович");
        Integer id = byLastAndFirstName.getId();
        Author byId = authorDao.getById(id);
        assertEquals(author, byId);
        authorDao.deleteById(id);
        assertThrows(EmptyResultDataAccessException.class,()->authorDao.getById(id));

    }
}