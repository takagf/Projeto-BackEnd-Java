package com.example.vendasms.service;

import java.util.List;

import com.example.vendasms.shared.VendaDto;
import java.util.Optional;

public interface VendaService {
    VendaDto criarVenda(VendaDto venda);
    List<VendaDto> obterTodos();
    Optional<VendaDto> obterPorId(String id);
    List<VendaDto> obterPorProduto(String produto);
    void removerVenda (String id);
    boolean definirComoNaoVendido(String id);
    VendaDto atualizarVenda(String id, VendaDto venda);
}
