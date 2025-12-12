package TP1.API.MVC.dto;

import TP1.API.MVC.domain.Category;
import jakarta.validation.constraints.*;

public class UpdateBookDto {

    @NotBlank
    private String title;

    @NotBlank
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "ISBN invalide")
    private String isbn;

    @NotNull
    @Min(1450)
    private Integer year;

    @NotNull
    private Category category;

    @NotNull
    private Long authorId;

    public String getTitle() { return title; }
    public String getIsbn() { return isbn; }
    public Integer getYear() { return year; }
    public Category getCategory() { return category; }
    public Long getAuthorId() { return authorId; }
}
