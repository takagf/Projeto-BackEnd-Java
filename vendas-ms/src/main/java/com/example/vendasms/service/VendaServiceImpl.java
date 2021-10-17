package com.example.vendasms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vendasms.model.Venda;
import com.example.vendasms.repository.VendaRepository;
import com.example.vendasms.shared.VendaDto;

@Service
public class VendaServiceImpl implements VendaService {
    @Autowired
    private VendaRepository repo;

    @Override
    public VendaDto criarVenda(VendaDto venda) {
        return salvarVenda(venda);
    }

    @Override
    public List<VendaDto> obterTodos() {
        List<Venda> vendas = repo.findAll();

        return vendas.stream()
            .map(venda -> new ModelMapper().map(venda, VendaDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<VendaDto> obterPorId(String id) {
        Optional<Venda> venda = repo.findById(id);

        if(venda.isPresent()) {
            return Optional.of(new ModelMapper().map(venda.get(), VendaDto.class));
        }

        return Optional.empty();
    }

    @Override
    public List<VendaDto> obterPorProduto(String produto) {
        List<Venda> vendas = repo.findByProduto(produto);
        
        return vendas.stream()
            .map(venda -> new ModelMapper().map(venda, VendaDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public void removerVenda(String id) {
        repo.deleteById(id);
    }

    @Override
    public boolean definirComoNaoVendido(String id) {
        Optional<Venda> venda = repo.findById(id);
        if(venda.isPresent()) {
            venda.get().setVendido(false);
            repo.save(venda.get());

            return true;
        }

        return false;
    }

    @Override
    public VendaDto atualizarVenda(String id, VendaDto venda){
        venda.setId(id);
        return salvarVenda(venda);
    }

    private VendaDto salvarVenda(VendaDto venda) {
        ModelMapper mapper = new ModelMapper();
        Venda vendaEntidade = mapper.map(venda, Venda.class);
        vendaEntidade = repo.save(vendaEntidade);

        return mapper.map(vendaEntidade, VendaDto.class);
    }
}
