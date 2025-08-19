package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.domain.dto.responseDto.LivroResponseDto;
import io.github.cursodsousa.libraryapi.domain.entity.Autor;
import io.github.cursodsousa.libraryapi.domain.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 *  @see LivroRepositoryTest
 */

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {
    boolean existsByIsbn(String isbn);

    // Query Method
    // select * from livro where id_autor = id
    List<Livro> findByAutor(Autor autor);

    // select * from livro where titulo = titulo
    List<Livro> findByTitulo(String titulo);

    // select * from livro where isbn = ?
    List<Livro> findByIsbn(String isbn);

    // select * from livro where titulo = ? and preco = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    // select * from livro where titulo = ? or isbn = ?
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    //=================================================================================================================

    // Métodos JPQL @Query

    //SELECT L.* FROM LIVRO as L ORDER BY L.TITULO
    @Query(" select l from Livro as l order by l.titulo, l.preco ")
    List<Livro> listarTodosOrdenadoPorTituloEpreco();

    //=================================================================================================================

    // Métodos JPQL @Query

    //select a.* from livro l join autor a on a.id = l.id_autor;
    @Query(" select a from Livro as l join l.autor as a")
    List<Autor> listarAutoresDosLivros();

    //=================================================================================================================

    // Métodos JPQL @Query

    //select distinct l.* from Livro as l
    @Query(" select distinct l.titulo from Livro as l ")
    List<String> listarNomesDiferentesLivros();

    //=================================================================================================================

    //Criando uma query com mais linhas
    @Query("""
          select l.genero
          from Livro as l
          join l.autor as a
          where a.nacionalidade = 'Brasileira'
          order by l.genero
    """)
    List<String> listarGenerosAutoresBrasileiros();
}
