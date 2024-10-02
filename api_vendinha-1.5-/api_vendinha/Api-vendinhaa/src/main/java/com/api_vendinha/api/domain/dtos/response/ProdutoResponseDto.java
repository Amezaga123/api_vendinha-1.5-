package com.api_vendinha.api.domain.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para representar os dados de um produto retornados nas respostas da API.
 */
@Data // Gera automaticamente métodos getters, setters, toString, equals e hashCode.
@NoArgsConstructor // Gera um construtor sem argumentos.
public class ProdutoResponseDto {

    /**
     * Identificador único do produto.
     */
    private Long id;

    /**
     * Nome do produto.
     */
    private String nome;

    /**
     * Quantidade disponível do produto.
     */
    private Integer quantidade;

    /**
     * Preço do produto.
     */
    private Double preco;

    /**
     * ID do usuário associado ao produto.
     */
    private Long user_id; // Adicione a declaração da variável user_id

    // Os métodos getters e setters são gerados automaticamente pelo Lombok devido à anotação @Data.
}
