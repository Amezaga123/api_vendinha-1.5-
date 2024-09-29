package com.api_vendinha.api.domain.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para representar os dados de um produto retornados nas respostas da API.
 */
@Data // Gera automaticamente métodos getters, setters, toString, equals e hashCode.
@NoArgsConstructor // Gera um construtor sem argumentos, necessário para a criação de instâncias pelo JPA e outras operações.
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

    public Long getId() {   
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
