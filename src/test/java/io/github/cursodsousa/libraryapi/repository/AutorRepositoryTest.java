package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.domain.entity.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Kinka");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1980, 06, 19));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizar(){
        var id = UUID.fromString("6acac106-dca0-423d-b9c8-3ec64c2b7548");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if(possivelAutor.isPresent()){

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setNome("Sousa");
            autorEncontrado.setNacionalidade("Japonesa");
            autorEncontrado.setDataNascimento(LocalDate.of(2000,9, 25));

            autorRepository.save(autorEncontrado);
        }



    }
}
