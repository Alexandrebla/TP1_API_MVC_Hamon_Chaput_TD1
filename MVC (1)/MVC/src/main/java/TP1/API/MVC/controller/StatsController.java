package TP1.API.MVC.controller;

import TP1.API.MVC.domain.Category;
import TP1.API.MVC.repository.BookRepository;
import TP1.API.MVC.repository.AuthorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;

    public StatsController(BookRepository bookRepo, AuthorRepository authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    // GET /stats/books-per-category
    @GetMapping("/books-per-category")
    public Map<Category, Long> booksPerCategory() {
        Map<Category, Long> map = new HashMap<>();
        for (Category c : Category.values()) {
            long count = bookRepo.count((root, query, cb) ->
                    cb.equal(root.get("category"), c));
            map.put(c, count);
        }
        return map;
    }

    // GET /stats/top-authors?limit=3
    @GetMapping("/top-authors")
    public List<Map<String, Object>> topAuthors(@RequestParam(defaultValue = "3") int limit) {

        List<Map<String, Object>> raw = bookRepo
                .findAll()
                .stream()
                .collect(
                        java.util.stream.Collectors.groupingBy(
                                b -> b.getAuthor().getLastName() + " " + b.getAuthor().getFirstName(),
                                java.util.stream.Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(limit)
                .map(e -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("author", e.getKey());
                    m.put("books", e.getValue());
                    return m;
                })
                .toList();


        return raw;
    }
}
