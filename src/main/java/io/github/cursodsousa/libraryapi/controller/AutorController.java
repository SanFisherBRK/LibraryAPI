package io.github.cursodsousa.libraryapi.controller;

import io.github.cursodsousa.libraryapi.domain.dto.requestDto.AutorRequestDto;
import io.github.cursodsousa.libraryapi.domain.dto.responseDto.AutorResponseDto;
import io.github.cursodsousa.libraryapi.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autor")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public AutorResponseDto salvar(@Valid @RequestBody AutorRequestDto dto){
        return autorService.salvar(dto);
    }
}
