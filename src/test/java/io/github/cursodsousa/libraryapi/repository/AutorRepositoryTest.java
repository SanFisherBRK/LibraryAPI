package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.domain.entity.Autor;
import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;

   /* @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Kinka");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1980, 06, 19));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }*/

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

    @Test
    public void listarTest(){
        List<Autor> list = autorRepository.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagens de autores: " + autorRepository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("f1c21ad7-aa4e-44b3-bf23-24b6c2764103");
        autorRepository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("53805273-3506-4aff-9f94-831a6f1fb96e");
        var maria = autorRepository.findById(id).get();
        autorRepository.delete(maria);
    }
}
