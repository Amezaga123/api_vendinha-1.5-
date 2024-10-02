package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;
import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.entities.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoServiceInterface {

    private final ProdutoRepository produtoRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository, UserRepository userRepository) {
        this.produtoRepository = produtoRepository;
        this.userRepository = userRepository; // Inicialize a variável
    }

    @Override
    public ProdutoResponseDto save(ProdutoRequestDto produtoRequestDto) {
        Produto produto = new Produto();
        produto.setNome(produtoRequestDto.getNome());
        produto.setQuantidade(produtoRequestDto.getQuantidade());
        produto.setPreco(produtoRequestDto.getPreco());

        User user = userRepository.findById(produtoRequestDto.getUser_id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        produto.setUser(user);

        Produto savedProduto = produtoRepository.save(produto);

        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto();
        produtoResponseDto.setId(savedProduto.getId());
        produtoResponseDto.setNome(savedProduto.getNome());
        produtoResponseDto.setQuantidade(savedProduto.getQuantidade());
        produtoResponseDto.setPreco(savedProduto.getPreco());
        produtoResponseDto.setUser_id(savedProduto.getUser().getId());

        return produtoResponseDto;
    }

    @Override
    public ProdutoResponseDto update(Long id, ProdutoRequestDto produtoRequestDto) {
        Produto produtoexiste = produtoRepository.findById(id).orElseThrow();
        produtoexiste.setNome(produtoRequestDto.getNome());
        produtoexiste.setQuantidade(produtoRequestDto.getQuantidade());
        produtoexiste.setPreco(produtoRequestDto.getPreco());

        produtoRepository.save(produtoexiste);

        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto();
        produtoResponseDto.setId(produtoexiste.getId());
        produtoResponseDto.setNome(produtoexiste.getNome());
        produtoResponseDto.setQuantidade(produtoexiste.getQuantidade());
        produtoResponseDto.setPreco(produtoexiste.getPreco());
        return produtoResponseDto;
    }

    @Override
    public List<ProdutoResponseDto> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public ProdutoResponseDto findById(Long id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        return optionalProduto.map(this::mapToResponseDto).orElse(null);
    }

    private ProdutoResponseDto mapToResponseDto(Produto produto) {
        ProdutoResponseDto dto = new ProdutoResponseDto();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setQuantidade(produto.getQuantidade());
        dto.setPreco(produto.getPreco());
        return dto;
    }

    public void updateStock(Long productId, Integer soldQuantity) {
        Produto produto = produtoRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produto.getQuantidade() < soldQuantity) {
            throw new RuntimeException("Estoque insuficiente");
        }

        produto.setQuantidade(produto.getQuantidade() - soldQuantity);
        produtoRepository.save(produto);
    }
}

