package TP1.API.MVC.mapper;

import TP1.API.MVC.domain.Author;
import TP1.API.MVC.domain.Book;
import TP1.API.MVC.dto.*;

import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private final AuthorMapper authorMapper;

    public BookMapper(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getYear(),
                book.getCategory(),
                authorMapper.toDto(book.getAuthor())
        );
    }

    public Book toEntity(CreateBookDto dto, Author author) {
        return new Book(
                dto.getTitle(),
                dto.getIsbn(),
                dto.getYear(),
                dto.getCategory(),
                author
        );
    }

    public void updateEntity(Book book, UpdateBookDto dto, Author author) {
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setYear(dto.getYear());
        book.setCategory(dto.getCategory());
        book.setAuthor(author);
    }
}
