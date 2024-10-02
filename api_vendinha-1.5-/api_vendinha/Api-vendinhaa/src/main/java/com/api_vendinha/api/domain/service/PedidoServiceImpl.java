package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.PedidoRepository;
import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;
import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.PedidoRequestDto;
import com.api_vendinha.api.domain.dtos.response.PedidoResponseDto;
import com.api_vendinha.api.domain.entities.Pedido;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoServiceInterface {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final UserRepository userRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, UserRepository userRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PedidoResponseDto realizarVenda(PedidoRequestDto pedidoRequestDto) {
        User user = userRepository.findById(pedidoRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Produto produto = produtoRepository.findById(pedidoRequestDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produto.getQuantidade() < pedidoRequestDto.getQuantity()) {
            throw new RuntimeException("Quantidade em estoque insuficiente");
        }

        // Atualiza a quantidade do produto
        produto.setQuantidade(produto.getQuantidade() - pedidoRequestDto.getQuantity());
        produtoRepository.save(produto);

        // Cria o pedido
        Pedido pedido = new Pedido();
        pedido.setUserId(user.getId());
        pedido.setProductId(produto.getId());
        pedido.setQuantity(pedidoRequestDto.getQuantity());
        pedido.setPrice(produto.getPreco() * pedidoRequestDto.getQuantity());

        Pedido savedPedido = pedidoRepository.save(pedido);

        // Cria o DTO de resposta
        PedidoResponseDto pedidoResponseDto = new PedidoResponseDto();
        pedidoResponseDto.setId(savedPedido.getId());
        pedidoResponseDto.setUserId(savedPedido.getUserId());
        pedidoResponseDto.setProductId(savedPedido.getProductId());
        pedidoResponseDto.setQuantity(savedPedido.getQuantity());
        pedidoResponseDto.setPrice(savedPedido.getPrice());

        return pedidoResponseDto;
    }
}
