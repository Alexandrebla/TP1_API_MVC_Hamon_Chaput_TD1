package TP1.API.MVC.service;

import TP1.API.MVC.domain.Author;
import TP1.API.MVC.exception.NotFoundException;
import TP1.API.MVC.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    // GET /authors
    public List<Author> getAll() {
        return repository.findAll();
    }

    // GET /authors/{id}
    public Author getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Auteur introuvable avec id = " + id));
    }

    // POST /authors
    public Author create(Author author) {
        return repository.save(author);
    }

    // PUT /authors/{id}
    public Author update(Long id, Author updated) {
        Author existing = getById(id);

        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setBirthYear(updated.getBirthYear());

        return repository.save(existing);
    }

    // DELETE /authors/{id}
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Auteur introuvable avec id = " + id);
        }
        repository.deleteById(id);
    }
}
