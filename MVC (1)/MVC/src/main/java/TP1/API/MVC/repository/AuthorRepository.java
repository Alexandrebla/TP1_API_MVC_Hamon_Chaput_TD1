package TP1.API.MVC.repository;

import TP1.API.MVC.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {}
