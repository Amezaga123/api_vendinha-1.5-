package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.PedidoRequestDto;
import com.api_vendinha.api.domain.dtos.response.PedidoResponseDto;
import com.api_vendinha.api.domain.service.PedidoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PedidoController {

    private final PedidoServiceInterface pedidoService;

    @Autowired
    public PedidoController(PedidoServiceInterface pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/pedido")
    public PedidoResponseDto realizarVenda(@RequestBody PedidoRequestDto pedidoRequestDto) {
        return pedidoService.realizarVenda(pedidoRequestDto);
    }
}
