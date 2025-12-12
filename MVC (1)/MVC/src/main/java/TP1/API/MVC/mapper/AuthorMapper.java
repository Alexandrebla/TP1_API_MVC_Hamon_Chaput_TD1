package TP1.API.MVC.mapper;

import TP1.API.MVC.domain.Author;
import TP1.API.MVC.dto.AuthorDto;
import TP1.API.MVC.dto.CreateAuthorDto;
import TP1.API.MVC.dto.UpdateAuthorDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDto toDto(Author author) {
        return new AuthorDto(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getBirthYear()
        );
    }

    public Author toEntity(CreateAuthorDto dto) {
        return new Author(dto.getFirstName(), dto.getLastName(), dto.getBirthYear());
    }

    public void updateEntity(Author author, UpdateAuthorDto dto) {
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setBirthYear(dto.getBirthYear());
    }
}
