package TP1.API.MVC.controller;

import TP1.API.MVC.dto.*;
import TP1.API.MVC.domain.Author;
import TP1.API.MVC.mapper.AuthorMapper;
import TP1.API.MVC.service.AuthorService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService service;
    private final AuthorMapper mapper;

    public AuthorController(AuthorService service, AuthorMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // GET /authors
    @GetMapping
    public List<AuthorDto> getAll() {
        return service.getAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    // GET /authors/{id}
    @GetMapping("/{id}")
    public AuthorDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getById(id));
    }

    // POST /authors
    @PostMapping
    public AuthorDto create(@Valid @RequestBody CreateAuthorDto dto) {
        Author author = mapper.toEntity(dto);
        return mapper.toDto(service.create(author));
    }

    // PUT /authors/{id}
    @PutMapping("/{id}")
    public AuthorDto update(@PathVariable Long id,
                            @Valid @RequestBody UpdateAuthorDto dto) {
        Author author = service.getById(id);
        mapper.updateEntity(author, dto);
        return mapper.toDto(service.update(id, author));
    }

    // DELETE /authors/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
