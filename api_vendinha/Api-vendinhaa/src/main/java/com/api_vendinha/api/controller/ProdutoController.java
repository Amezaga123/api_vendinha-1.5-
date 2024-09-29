package com.api_vendinha.api.controller;


import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.service.ProdutoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // Define o caminho base para as requisições deste controlador.
public class ProdutoController {

    // Injeção de dependência do serviço de usuários.
    private final ProdutoServiceInterface produtoService;

    /**
     * Construtor para injeção de dependência do serviço de usuários.
     *
     * @param produtoService O serviço de usuários a ser injetado.
     */
    @Autowired
    public ProdutoController(ProdutoServiceInterface produtoService) {
        this.produtoService = produtoService;
    }

    /**
     * Método para salvar um novo usuário.
     *
     * @param produtoRequestDto DTO que contém os dados do usuário a ser salvo.
     * @return DTO com as informações do usuário salvo, incluindo o ID gerado.
     */
   @PostMapping("/produtos") // Define que este método lida com requisições HTTP POST.
    public ProdutoResponseDto salvar(@RequestBody ProdutoRequestDto produtoRequestDto) {
        // Chama o serviço para salvar o usuário e retorna a resposta.
        return produtoService.save(produtoRequestDto);
   }
    @PutMapping("/produtos/{id}")
    public ProdutoResponseDto update(@PathVariable Long id,@RequestBody ProdutoRequestDto produtoRequestDto) {
        // Chama o serviço para salvar o usuário e retorna a resposta.
        return produtoService.update(id,produtoRequestDto);
    }

    @GetMapping("/produtos")
    public List<ProdutoResponseDto> listarTodos() {
        return produtoService.findAll();
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProdutoResponseDto> encontrarPorId(@PathVariable Long id) {
        ProdutoResponseDto produto = produtoService.findById(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}


