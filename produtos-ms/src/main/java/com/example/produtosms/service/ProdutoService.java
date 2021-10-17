package com.example.produtosms.service;

import java.util.List;
import java.util.Optional;

import com.example.produtosms.shared.ProdutoDto;

public interface ProdutoService {
    ProdutoDto criarProduto(ProdutoDto produto);
    List<ProdutoDto> obterTodos();
    Optional<ProdutoDto> obterPorId(String id);
    void removerProduto(String id);
    ProdutoDto atualizarProduto(String id, ProdutoDto produto);
}
