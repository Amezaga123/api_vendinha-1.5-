package com.api_vendinha.api.domain.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoResponseDto {
    private Long id;           // ID do pedido
    private Long userId;      // ID do usuário
    private Long productId;   // ID do produto
    private Integer quantity;  // Quantidade vendida
    private Double price;      // Preço total
}
