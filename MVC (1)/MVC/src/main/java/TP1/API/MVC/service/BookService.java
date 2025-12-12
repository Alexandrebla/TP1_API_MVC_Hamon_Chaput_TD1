package TP1.API.MVC.service;

import TP1.API.MVC.domain.Book;
import TP1.API.MVC.domain.Author;
import TP1.API.MVC.exception.BadRequestException;
import TP1.API.MVC.exception.NotFoundException;
import TP1.API.MVC.repository.BookRepository;
import TP1.API.MVC.repository.AuthorRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    private void validateYear(Integer year) {
        int current = Year.now().getValue();
        if (year < 1450 || year > current) {
            throw new BadRequestException("Année invalide. Doit être entre 1450 et " + current);
        }
    }

    private Author getAuthorOrThrow(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Auteur introuvable avec id = " + id));
    }

    // GET /books (pagination + filtres)
    public Page<Book> search(org.springframework.data.jpa.domain.Specification<Book> spec,
                             Pageable pageable) {
        return bookRepository.findAll(spec, pageable);
    }

    // GET /books/{id}
    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Livre introuvable avec id = " + id));
    }

    // POST /books
    public Book create(Book book) {
        validateYear(book.getYear());

        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new BadRequestException("ISBN déjà utilisé : " + book.getIsbn());
        }

        Author author = getAuthorOrThrow(book.getAuthor().getId());
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    // PUT /books/{id}
    public Book update(Long id, Book updated) {
        Book existing = getById(id);

        validateYear(updated.getYear());

        // si on modifie l'ISBN, vérifier qu'il n'est pas utilisé par un autre livre
        if (!existing.getIsbn().equals(updated.getIsbn()) &&
                bookRepository.existsByIsbn(updated.getIsbn())) {
            throw new BadRequestException("ISBN déjà utilisé : " + updated.getIsbn());
        }

        Author author = getAuthorOrThrow(updated.getAuthor().getId());

        existing.setTitle(updated.getTitle());
        existing.setIsbn(updated.getIsbn());
        existing.setYear(updated.getYear());
        existing.setCategory(updated.getCategory());
        existing.setAuthor(author);

        return bookRepository.save(existing);
    }

    // DELETE /books/{id}
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException("Livre introuvable avec id = " + id);
        }
        bookRepository.deleteById(id);
    }
}
