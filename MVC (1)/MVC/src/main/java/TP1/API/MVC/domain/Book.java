package TP1.API.MVC.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(
        name = "books",
        uniqueConstraints = @UniqueConstraint(columnNames = "isbn")
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Pattern(
            regexp = "^(97(8|9))?\\d{9}(\\d|X)$",
            message = "ISBN invalide"
    )
    @Column(nullable = false, unique = true)
    private String isbn;

    @NotNull
    @Min(1450)
    private Integer year;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Book() {}

    public Book(String title, String isbn, Integer year, Category category, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.category = category;
        this.author = author;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
}
