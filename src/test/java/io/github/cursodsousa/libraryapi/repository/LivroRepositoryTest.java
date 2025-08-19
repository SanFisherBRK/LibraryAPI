package io.github.cursodsousa.libraryapi.repository;

//import static org.junit.jupiter.api.Assertions.*;

import io.github.cursodsousa.libraryapi.domain.entity.Autor;
import io.github.cursodsousa.libraryapi.domain.entity.Livro;
import io.github.cursodsousa.libraryapi.domain.enums.GeneroLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    //================================================================================================================
    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro Livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("76e7c418-ccf9-4e2a-af20-c28b9e50ab55"))
                .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }
    //================================================================================================================
    @Test
    void salvarAutorELivroTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Terceiro Livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("José");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }
    //================================================================================================================
    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro Livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        livro.setAutor(autor);

        repository.save(livro);
    }
    //================================================================================================================
    @Test
    void atualizarAutorDoLivro(){
        UUID id = UUID.fromString("cfbc87ce-5932-4792-bff0-78ef5973861b");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("76e7c418-ccf9-4e2a-af20-c28b9e50ab55");
        Autor maria = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(maria);

        repository.save(livroParaAtualizar);
    }
    //================================================================================================================
    @Test
    void deletar(){
        UUID id = UUID.fromString("cfbc87ce-5932-4792-bff0-78ef5973861b");
        repository.deleteById(id);
    }
    //================================================================================================================
    @Test
    void deletarCascade(){
        UUID id = UUID.fromString("22238c02-8118-45ba-a9f0-202dfc3acc67");
        repository.deleteById(id);
    }
    //================================================================================================================
    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("daed83b3-65fd-49eb-9400-cbc0af13059d");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());

//        System.out.println("Autor:");
//        System.out.println(livro.getAutor().getNome());
    }
    //================================================================================================================
    @Test
    void pesquisaPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("O roubo da casa assombrada");
        lista.forEach(System.out::println);
    }

    //================================================================================================================
    @Test
    void pesquisaPorISBNTest(){
        List<Livro> lista = repository.findByIsbn("20847-84874");
        lista.forEach(System.out::println);
    }

    //================================================================================================================
    @Test
    void pesquisaPorTituloEPrecoTest(){
        var preco = BigDecimal.valueOf(204.00);
        var tituloPesquisa = "O roubo da casa assombrada";

        List<Livro> lista = repository.findByTituloAndPreco(tituloPesquisa, preco);
        lista.forEach(System.out::println);
    }

    //================================================================================================================

    // Método JPQL @Query
    @Test
    void listarLivrosComQueryJPQL(){
        var resultado = repository.listarTodosOrdenadoPorTituloEpreco();
        resultado.forEach(System.out::println);
    }

    //================================================================================================================

    // Método JPQL @Query
    @Test
    void listarAutoresDosLivrosJPQL(){
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    //================================================================================================================

    // Método JPQL @Query

    @Test
    void listarTitulosNaoRepetidosDosLivrosJPQL(){
        var resultado = repository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    //================================================================================================================

    // Método JPQL @Query

    @Test
    void listarGenerosDeLivrosAutoresBrasileirosJPQL(){
        var resultado = repository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }
}