package io.github.cursodsousa.libraryapi.domain.dto.requestDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class AutorRequestDto {

    private UUID id;
    @NotBlank(message = "Preencha o campo nome")
    private String nome;
    @NotNull(message = "O campo data não pode ser vazio")
    private LocalDate dataNascimento;
    @NotBlank(message = "Preencha o campo nome")
    private String nacionalidade;

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
}
