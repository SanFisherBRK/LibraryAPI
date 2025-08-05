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
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {
Scanner sc = new Scanner(System.in);
    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    void salvaTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        System.out.print("Digite o preço: ");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));


        Autor autorExist = autorRepository.findById(UUID.fromString("8bf7e127-7f8c-4f36-9494-69060aa02eee")).orElse(null);
        livro.setAutor(autorExist);

        livroRepository.save(livro);
    }

//=============================================================================================================================

    //Salvando o autor e o livro de uma só vez
    @Test
    void salvarAutorELivroTest(){
        // Cria um novo livro
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Terceiro Livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        // Cria um novo autor e o salva
        Autor autor = new Autor();
        autor.setNome("José");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));
        autorRepository.save(autor);

        // Associa o autor recém-criado ao livro
        livro.setAutor(autor);

        // Persiste o livro no banco
        livroRepository.save(livro);
    }
//=============================================================================================================================

    //Usando "Cascade" para salvar o livro e o autor de modo automático
    @Test
    void salvarCascadeTest(){
        // Cria um livro e um autor (sem salvar o autor manualmente)
        // Isso testa o comportamento de "cascade persist" se estiver configurado na entidade Livro
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

        // Testa se o autor é salvo automaticamente via cascade
        livroRepository.save(livro);
    }

//=============================================================================================================================

    //
    @Test
    void atualizarAutorDoLivro(){
        // Atualiza o autor de um livro existente no banco
        UUID id = UUID.fromString("cfbc87ce-5932-4792-bff0-78ef5973861b");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        // Busca outro autor para substituir
        UUID idAutor = UUID.fromString("76e7c418-ccf9-4e2a-af20-c28b9e50ab55");
        Autor maria = autorRepository.findById(idAutor).orElse(null);

        // Atualiza e salva novamente
        livroParaAtualizar.setAutor(maria);
        livroRepository.save(livroParaAtualizar);
    }

//=============================================================================================================================

    @Test
    void deletar(){
        // Remove um livro do banco de dados pelo ID
        UUID id = UUID.fromString("cfbc87ce-5932-4792-bff0-78ef5973861b");
        livroRepository.deleteById(id);
    }
//=============================================================================================================================

    @Test
    void deletarCascade(){
        // Tenta deletar um livro e verifica se o autor também será removido
        // Isso depende do comportamento de "cascade delete" ou configurações de FK no banco
        UUID id = UUID.fromString("22238c02-8118-45ba-a9f0-202dfc3acc67");
        livroRepository.deleteById(id);
    }
//=============================================================================================================================

    //Buscar um livro e trazer o autor junto
    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("db0362e6-6058-4162-85a2-6ce21b40c2b6");
        Livro livro = livroRepository.findById(id).orElse(null);

        System.out.println("Livro");
        assert livro != null;
        System.out.println(livro.getTitulo());

        System.out.println("Autor");
        System.out.println(livro.getAutor().getNome());
    }
//=============================================================================================================================
}