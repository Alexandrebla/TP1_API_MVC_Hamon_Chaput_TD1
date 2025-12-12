package TP1.API.MVC.spec;

import TP1.API.MVC.domain.Book;
import TP1.API.MVC.domain.Category;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    public static Specification<Book> titleContains(String title) {
        return (root, query, cb) ->
                title == null ? null : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Book> hasAuthor(Long authorId) {
        return (root, query, cb) ->
                authorId == null ? null : cb.equal(root.get("author").get("id"), authorId);
    }

    public static Specification<Book> hasCategory(Category category) {
        return (root, query, cb) ->
                category == null ? null : cb.equal(root.get("category"), category);
    }

    public static Specification<Book> yearFrom(Integer year) {
        return (root, query, cb) ->
                year == null ? null : cb.greaterThanOrEqualTo(root.get("year"), year);
    }

    public static Specification<Book> yearTo(Integer year) {
        return (root, query, cb) ->
                year == null ? null : cb.lessThanOrEqualTo(root.get("year"), year);
    }
}
