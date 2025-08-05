package io.github.cursodsousa.libraryapi.service;

import io.github.cursodsousa.libraryapi.domain.dto.requestDto.AutorRequestDto;
import io.github.cursodsousa.libraryapi.domain.dto.requestDto.LivroRequestDto;
import io.github.cursodsousa.libraryapi.domain.dto.responseDto.AutorResponseDto;
import io.github.cursodsousa.libraryapi.domain.dto.responseDto.LivroResponseDto;
import io.github.cursodsousa.libraryapi.domain.entity.Autor;
import io.github.cursodsousa.libraryapi.domain.entity.Livro;
import io.github.cursodsousa.libraryapi.repository.AutorRepository;
import io.github.cursodsousa.libraryapi.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AutorService {
    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    public AutorService(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
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
//=========================================================================================================================

//Ative em Autor, a linha //@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
 //Para usar o Cascade e salvar autor e livros juntos
 @Transactional
 public AutorResponseDto salvarAutorComLivros(AutorRequestDto dto) {


     // Verificar se já existe autor com mesmo nome + dataNascimento
     Optional<Autor> autorExistente = autorRepository.findByNomeAndDataNascimento(
             dto.getNome(), dto.getDataNascimento()
     );


     if(autorExistente.isPresent()){
         throw new RuntimeException("Autor já cadastrado com esse nome e data de nascimento.");
     }

     // Verificar ISBN duplicado
     for(LivroRequestDto livroDto : dto.getLivros()){
         if(livroRepository.existsByIsbn(livroDto.getIsbn())){
             throw new RuntimeException("Livro com ISBN " + livroDto.getIsbn() + " já está cadastrado.");
         }
     }


     Autor autor = new Autor();
     autor.setNome(dto.getNome());
     autor.setNacionalidade(dto.getNacionalidade());
     autor.setDataNascimento(dto.getDataNascimento());

     List<Livro> livros = dto.getLivros().stream().map(livroDto -> {
         Livro livro = new Livro();
         livro.setIsbn(livroDto.getIsbn());
         livro.setTitulo(livroDto.getTitulo());
         livro.setGenero(livroDto.getGenero()); // Enum
         livro.setPreco(livroDto.getPreco());
         livro.setDataPublicacao(livroDto.getDataPublicacao());
         livro.setAutor(autor); // chave estrangeira
         return livro;
     }).collect(Collectors.toList());

     autor.setLivros(livros);

     Autor autorSalvo = autorRepository.save(autor); // cascade = ALL garante persistência dos livros

     // Montar AutorResponseDto (ou usar um mapper)
     AutorResponseDto response = new AutorResponseDto();
     response.setId(autorSalvo.getId());
     response.setNome(autorSalvo.getNome());
     response.setNacionalidade(autorSalvo.getNacionalidade());
     response.setDataNascimento(autorSalvo.getDataNascimento());

     List<LivroResponseDto> livrosDto = autorSalvo.getLivros().stream().map(l -> {
         LivroResponseDto dtoLivro = new LivroResponseDto();
         dtoLivro.setId(l.getId());
         dtoLivro.setTitulo(l.getTitulo());
         dtoLivro.setGenero(l.getGenero());
         dtoLivro.setIsbn(l.getIsbn());
         dtoLivro.setPreco(l.getPreco());
         dtoLivro.setDataPublicacao(l.getDataPublicacao());
         return dtoLivro;
     }).collect(Collectors.toList());

     response.setLivros(livrosDto);

     return response;
 }

}
