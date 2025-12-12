package TP1.API.MVC.dto;

import TP1.API.MVC.domain.Category;

public class BookDto {

    private Long id;
    private String title;
    private String isbn;
    private Integer year;
    private Category category;

    private AuthorDto author;

    public BookDto(Long id, String title, String isbn, Integer year, Category category, AuthorDto author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.category = category;
        this.author = author;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getIsbn() { return isbn; }
    public Integer getYear() { return year; }
    public Category getCategory() { return category; }
    public AuthorDto getAuthor() { return author; }
}
