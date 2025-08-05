package io.github.cursodsousa.libraryapi.domain.dto.responseDto;

import io.github.cursodsousa.libraryapi.domain.entity.Autor;
import io.github.cursodsousa.libraryapi.domain.entity.Livro;

import java.time.LocalDate;

import java.util.List;
import java.util.UUID;

public class AutorResponseDto {

    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private String nacionalidade;
    private List<LivroResponseDto> livros;

    public AutorResponseDto() {
    }

    public AutorResponseDto(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.dataNascimento = autor.getDataNascimento();
        this.nacionalidade = autor.getNacionalidade();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public List<LivroResponseDto> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroResponseDto> livros) {
        this.livros = livros;
    }
}
