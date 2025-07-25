package io.github.cursodsousa.libraryapi.service;

import io.github.cursodsousa.libraryapi.domain.dto.requestDto.AutorRequestDto;
import io.github.cursodsousa.libraryapi.domain.dto.responseDto.AutorResponseDto;
import io.github.cursodsousa.libraryapi.domain.entity.Autor;
import io.github.cursodsousa.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public AutorResponseDto salvar(AutorRequestDto dto){
        Autor autor = new Autor();
        autor.setNome(dto.getNome());
        autor.setNacionalidade(dto.getNacionalidade());
        autor.setDataNascimento(dto.getDataNascimento());

        Autor autorSalvo = autorRepository.save(autor);

        return new AutorResponseDto(autorSalvo);
    }
}
