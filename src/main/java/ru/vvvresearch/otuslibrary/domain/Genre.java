package ru.vvvresearch.otuslibrary.domain;

import java.util.Objects;

public class Genre {

    private Integer id;
    private String title;

    public Genre(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Genre(String title) {
        this(null, title);
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

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}'+'\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return Objects.equals(getTitle(), genre.getTitle());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getTitle());
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id = null;
        private String title;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Genre build() {
            return new Genre(id, title);
        }
    }
}
