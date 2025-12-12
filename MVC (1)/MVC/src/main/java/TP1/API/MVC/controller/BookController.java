package TP1.API.MVC.controller;

import TP1.API.MVC.domain.Author;
import TP1.API.MVC.domain.Book;
import TP1.API.MVC.domain.Category;

import TP1.API.MVC.dto.*;
import TP1.API.MVC.mapper.BookMapper;
import TP1.API.MVC.service.AuthorService;
import TP1.API.MVC.service.BookService;
import TP1.API.MVC.spec.BookSpecifications;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;
    private final AuthorService authorService;
    private final BookMapper mapper;

    public BookController(BookService service,
                          AuthorService authorService,
                          BookMapper mapper) {
        this.service = service;
        this.authorService = authorService;
        this.mapper = mapper;
    }

    // GET /books?title=&authorId=&category=&yearFrom=&yearTo=
    @GetMapping
    public Page<BookDto> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Integer yearFrom,
            @RequestParam(required = false) Integer yearTo,
            Pageable pageable
    ) {
        Specification<Book> spec = Specification.where(BookSpecifications.titleContains(title))
                .and(BookSpecifications.hasAuthor(authorId))
                .and(BookSpecifications.hasCategory(category))
                .and(BookSpecifications.yearFrom(yearFrom))
                .and(BookSpecifications.yearTo(yearTo));

        return service.search(spec, pageable)
                .map(mapper::toDto);
    }

    // GET /books/{id}
    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getById(id));
    }

    // POST /books
    @PostMapping
    public BookDto create(@Valid @RequestBody CreateBookDto dto) {
        Author author = authorService.getById(dto.getAuthorId());
        Book book = mapper.toEntity(dto, author);
        return mapper.toDto(service.create(book));
    }

    // PUT /books/{id}
    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id,
                          @Valid @RequestBody UpdateBookDto dto) {
        Author author = authorService.getById(dto.getAuthorId());
        Book existing = service.getById(id);
        mapper.updateEntity(existing, dto, author);
        return mapper.toDto(service.update(id, existing));
    }

    // DELETE /books/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
