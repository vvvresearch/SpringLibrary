package ru.vvvresearch.otuslibrary.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Author {
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
    private Calendar birthDate;
    private Locale locale;

    public Author(Integer id, String firstName, String lastName, String gender, Calendar birthDate, Locale locale) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.locale = locale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Author) {
            Author o = (Author) obj;
            return o.firstName.equals(firstName) &&
                    o.lastName.equals(lastName) &&
                    o.birthDate.equals(birthDate) &&
                    o.gender.equals(gender) &&
                    o.locale.equals(locale);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate.getTime() +
                ", locale=" + locale +
                '}'+'\n';
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id = null;
        private String firstName;
        private String lastName;
        private String gender;
        private Calendar birthDate;
        private Locale locale;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setBirthDate(Calendar birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder setLocale(Locale locale) {
            this.locale = locale;
            return this;
        }

        public Author build() {
            return new Author(id, firstName, lastName, gender, birthDate, locale);
        }
    }
}
