package ru.vvvresearch.otuslibrary.domain;

import java.util.Objects;

public class Book {

    private Integer id;
    private String title;
    private String publisher;
    private String publishYear;
    private Author author;
    private Genre genre;


    public Book(Integer id, String title, String publisher, String publishYear, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.author = author;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishYear='" + publishYear + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getPublisher(), book.getPublisher()) &&
                Objects.equals(getPublishYear(), book.getPublishYear()) &&
                Objects.equals(getAuthor(), book.getAuthor()) &&
                Objects.equals(getGenre(), book.getGenre());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getTitle(), getPublisher(), getPublishYear(), getAuthor(), getGenre());
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id = null;
        private String title;
        private String publisher;
        private String publishYear;
        private Author author;
        private Genre genre;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder setPublishYear(String publishYear) {
            this.publishYear = publishYear;
            return this;
        }

        public Builder setAuthor(Author author) {
            this.author = author;
            return this;
        }

        public Builder setGenre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public Book build() {
            return new Book(id, title, publisher, publishYear, author, genre);
        }
    }
}
