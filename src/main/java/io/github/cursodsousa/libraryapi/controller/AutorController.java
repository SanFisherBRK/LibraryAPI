package io.github.cursodsousa.libraryapi.controller;

import io.github.cursodsousa.libraryapi.domain.dto.requestDto.AutorRequestDto;
import io.github.cursodsousa.libraryapi.domain.dto.responseDto.AutorResponseDto;
import io.github.cursodsousa.libraryapi.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/autor")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

//==================================================================================================================
    @GetMapping
    public ResponseEntity<AutorResponseDto> salvar(@Valid @RequestBody AutorRequestDto dto){
        return ResponseEntity.ok(autorService.salvar(dto));
    }

//==================================================================================================================
    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDto> atualizar(@PathVariable UUID id, @Valid @RequestBody AutorRequestDto dto) {
        AutorResponseDto atualizado = autorService.atualizar(id, dto);

        return ResponseEntity.ok(atualizado);
    }

//==================================================================================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        autorService.delete(id);

        return ResponseEntity.noContent().build();
    }
//==================================================================================================================
}
