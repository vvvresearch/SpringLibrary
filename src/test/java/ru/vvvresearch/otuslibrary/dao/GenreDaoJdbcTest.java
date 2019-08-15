package ru.vvvresearch.otuslibrary.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.vvvresearch.otuslibrary.dao.interfaces.GenreDao;
import ru.vvvresearch.otuslibrary.domain.Genre;

import java.util.List;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class GenreDaoJdbcTest {
    @Autowired
    GenreDao genreDao;


    @Test
    public void count() {
        assertTrue(genreDao.count()>0);
    }

    @Test
    public void insert() {
        genreDao.insert(new Genre.Builder().setTitle("TestGenre").build());
        Genre genre = genreDao.getByGenreName("TestGenre");
        assertEquals(genre.getTitle(),"TestGenre");
    }

    @Test
    public void getById() {
        Genre genre = genreDao.getById(1);
        assertEquals(genre.getTitle(),"Роман");
    }

    @Test
    public void getAll() {
        List<Genre> genreList = genreDao.getAll();
        assertNotNull(genreList);
        assertTrue(genreList.size()>0);
    }

    @Test
    public void deleteById() {
        genreDao.deleteById(1);
        Genre genre = genreDao.getById(1);
        assertNull(genre);
    }
}