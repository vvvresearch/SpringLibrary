INSERT into AUTHORS (id,
                     first_name,
                     last_name,
                     gender,
                     birthdate,
                     country_key) values
  (1, 'Александр Сергеевич', 'Пушкин', 'М', to_date('06-06-1799', 'DD-MM-YYYY'), 'RU');
INSERT into GENRE (id, genre_name) values (1, 'Роман');
INSERT into GENRE (id, genre_name) values (2, 'Рассказ');
INSERT into GENRE (id, genre_name) values (3, 'Новелла');
INSERT into GENRE (id, genre_name) values (4, 'Стихи');
INSERT into GENRE (id, genre_name) values (5, 'Поэма');
INSERT into BOOKS (title,
                   publisher,
                   publish_year,
                   authors_id,
                   genre_id) values
  ('Евгений Онегин', 'Русиздат', '2001', 1, 5);
INSERT into BOOKS (title,
                   publisher,
                   publish_year,
                   authors_id,
                   genre_id) values
  ('Капитанская дочь', 'Совоблиздат', '1982', 1, 2);
