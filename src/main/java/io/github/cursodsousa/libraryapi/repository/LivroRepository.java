package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.domain.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
