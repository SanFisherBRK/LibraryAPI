package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.domain.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {
    boolean existsByIsbn(String isbn);
}
