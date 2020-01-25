package pl.sdacademy.intermediate.basic.basic8;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

/**
 * @author Piotr Zietek
 */
@Builder
@ToString
@Getter
class Book {

    private String title;
    private String author;
    private int yearPushlished;
    private int numberOfPages;
    private double price;
    private Genre genre;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        return yearPushlished == book.yearPushlished &&
                numberOfPages == book.numberOfPages &&
                title.equals(book.title) &&
                author.equals(book.author) &&
                genre == book.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, yearPushlished, numberOfPages, genre);
    }
}
