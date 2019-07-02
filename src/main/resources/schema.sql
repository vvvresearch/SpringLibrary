CREATE TABLE IF NOT EXISTS AUTHORS (
  author_id   INT AUTO_INCREMENT PRIMARY KEY,
  first_name  VARCHAR(100),
  last_name   VARCHAR(100),
  gender      CHAR(1),
  birthdate   DATE,
  country_key CHAR(2)
);
CREATE TABLE IF NOT EXISTS GENRE (
  genre_id    INT AUTO_INCREMENT PRIMARY KEY,
  genre_name VARCHAR(100),
);
CREATE TABLE IF NOT EXISTS BOOKS (
  book_id     INT AUTO_INCREMENT PRIMARY KEY,
  title        VARCHAR(255),
  publisher    VARCHAR(255),
  publish_year CHAR(4),
  author_id   INT NOT NULL,
  genre_id     INT NOT NULL,
  FOREIGN KEY (author_id) REFERENCES AUTHORS (author_id),
  FOREIGN KEY (genre_id) REFERENCES GENRE (genre_id)
);