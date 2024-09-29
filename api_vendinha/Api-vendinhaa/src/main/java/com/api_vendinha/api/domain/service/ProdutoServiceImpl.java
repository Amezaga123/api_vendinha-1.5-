package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;
import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoServiceInterface {

    private final ProdutoRepository produtoRepository;
    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    /**
     * Salva um novo usuário ou atualiza um usuário existente.
     *
     * Cria uma nova entidade User a partir dos dados fornecidos no UserRequestDto, persiste essa
     * entidade no banco de dados, e retorna um UserResponseDto com as informações do usuário salvo.
     *
     * @param produtoRequestDto DTO contendo os dados do usuário a ser salvo ou atualizado.
     * @return DTO com as informações do usuário salvo, incluindo o ID gerado e o nome.
     */
    @Override
    public ProdutoResponseDto save(ProdutoRequestDto produtoRequestDto) {
        // Cria uma nova instância de User.
        Produto produto = new Produto();
        // Define o nome do usuário a partir do DTO.
        produto.setNome(produtoRequestDto.getNome());
        produto.setQuantidade(produtoRequestDto.getQuantidade());
        produto.setPreco(produtoRequestDto.getPreco());


        // Salva o usuário no banco de dados e obtém a entidade persistida com o ID gerado.
        Produto savedProduto = produtoRepository.save(produto);

        // Cria um DTO de resposta com as informações do usuário salvo.
        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto();
        produtoResponseDto.setId(savedProduto.getId());
        produtoResponseDto.setNome(savedProduto.getNome());
        produtoResponseDto.setQuantidade(savedProduto.getQuantidade());
        produtoResponseDto.setPreco(savedProduto.getPreco());


        // Retorna o DTO com as informações do usuário salvo.
        return produtoResponseDto;
    }

    @Override
    public ProdutoResponseDto update(Long id, ProdutoRequestDto produtoRequestDto) {
        Produto produtoexiste= produtoRepository.findById(id).orElseThrow();
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
