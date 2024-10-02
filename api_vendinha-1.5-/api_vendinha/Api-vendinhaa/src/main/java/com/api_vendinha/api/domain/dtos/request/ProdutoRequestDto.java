package com.api_vendinha.api.domain.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para representar os dados necessários para criar ou atualizar um produto.
 */
@Data // Gera automaticamente métodos getters, setters, toString, equals e hashCode.
@NoArgsConstructor // Gera um construtor sem argumentos, necessário para a criação de instâncias pelo JPA e outras operações.
public class ProdutoRequestDto {

    /**
     * Nome do produto.
     *
     * Este campo é obrigatório e será utilizado para criar ou atualizar um produto no sistema.
     */
    private String nome;

    /**
     * Quantidade disponível do produto.
     *
     * Este campo é obrigatório e representa a quantidade de estoque disponível.
     */
    private Integer quantidade;

    /**
     * Preço do produto.
     *
     * Este campo é obrigatório e representa o valor monetário do produto.
     */
    private Double preco;

    private Long user_id;

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

    public Long getUser_id() {
        return user_id; // Retorna o valor do campo user_id
    }

}
