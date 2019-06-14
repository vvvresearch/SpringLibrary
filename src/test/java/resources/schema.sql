CREATE TABLE IF NOT EXISTS AUTHORS (
  id          INT AUTO_INCREMENT PRIMARY KEY,
  first_name  VARCHAR(100),
  last_name   VARCHAR(100),
  gender      CHAR(1),
  birthdate   DATE,
  country_key CHAR(2)
);
CREATE TABLE IF NOT EXISTS GENRE (
  id    INT AUTO_INCREMENT PRIMARY KEY,
  genre_name VARCHAR(100),
);
CREATE TABLE IF NOT EXISTS BOOKS (
  id           INT AUTO_INCREMENT PRIMARY KEY,
  title        VARCHAR(255),
  publisher    VARCHAR(255),
  publish_year CHAR(4),
  authors_id   INT NOT NULL,
  genre_id     INT NOT NULL,
  FOREIGN KEY (authors_id) REFERENCES AUTHORS (id),
  FOREIGN KEY (genre_id) REFERENCES GENRE (id)
);