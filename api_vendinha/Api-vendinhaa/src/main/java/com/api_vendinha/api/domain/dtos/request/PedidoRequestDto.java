package com.api_vendinha.api.domain.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoRequestDto {
    private Long userId;       // ID do usuário
    private Long productId;    // ID do produto
    private Integer quantity;   // Quantidade a ser vendida
}
