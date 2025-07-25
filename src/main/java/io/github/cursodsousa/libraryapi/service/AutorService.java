package io.github.cursodsousa.libraryapi.service;

import io.github.cursodsousa.libraryapi.domain.dto.requestDto.AutorRequestDto;
import io.github.cursodsousa.libraryapi.domain.dto.responseDto.AutorResponseDto;
import io.github.cursodsousa.libraryapi.domain.entity.Autor;
import io.github.cursodsousa.libraryapi.repository.AutorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

//====================================================================================================================

    @Transactional
    public AutorResponseDto salvar(AutorRequestDto dto){
        Autor autor = new Autor();
        autor.setNome(dto.getNome());
        autor.setNacionalidade(dto.getNacionalidade());
        autor.setDataNascimento(dto.getDataNascimento());

        Autor autorSalvo = autorRepository.save(autor);

        return new AutorResponseDto(autorSalvo);
    }

//====================================================================================================================

    @Transactional
    public AutorResponseDto atualizar(UUID id, @Valid AutorRequestDto dto) {
        Autor autorExistente = autorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor com ID " + id + " não encontrado."));

        autorExistente.setNome(dto.getNome());
        autorExistente.setNacionalidade(dto.getNacionalidade());
        autorExistente.setDataNascimento(dto.getDataNascimento());

        Autor autorAtualizado = autorRepository.save(autorExistente);

        return new AutorResponseDto(autorAtualizado);
    }

//====================================================================================================================
    public void delete(UUID id){

        Autor autor = autorRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Autor com ID \" + id + \" não encontrado."));
        autorRepository.delete(autor);
    }
//====================================================================================================================
}
