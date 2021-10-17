package com.example.vendasms.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vendasms.shared.VendaDto;
import com.example.vendasms.service.VendaService;
import com.example.vendasms.view.model.VendaModelAlteracao;
import com.example.vendasms.view.model.VendaModelInclusao;
import com.example.vendasms.view.model.VendaModelResponse;

@RestController
@RequestMapping("api/vendas")
public class VendaController {
    @Autowired
    private VendaService service;

    @GetMapping(value="/status")
    public String statusService(@Value("${local.server.port}") String porta) {
        return String.format("Serviço ativo e executando na porta %s", porta);
    }
    
    @PostMapping
    public ResponseEntity<VendaModelResponse> criarVenda(@RequestBody @Valid VendaModelInclusao Venda) {
        ModelMapper mapper = new ModelMapper();
        VendaDto dto = mapper.map(Venda, VendaDto.class);
        dto = service.criarVenda(dto);
        return new ResponseEntity<>(mapper.map(dto, VendaModelResponse.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VendaModelResponse>> obterTodos() {
        List<VendaDto> dtos = service.obterTodos();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<VendaModelResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, VendaModelResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value="/{produto}/lista")
    public ResponseEntity<List<VendaModelResponse>> obterPorProduto(@PathVariable String produto) {
        List<VendaDto> dtos = service.obterPorProduto(produto);

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<VendaModelResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, VendaModelResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<VendaModelResponse> obterPorId(@PathVariable String id) {
        Optional<VendaDto> Venda = service.obterPorId(id);

        if(Venda.isPresent()) {
            return new ResponseEntity<>(
                new ModelMapper().map(Venda.get(), VendaModelResponse.class), 
                HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity<VendaModelResponse> atualizarVenda(@PathVariable String id,
        @Valid @RequestBody VendaModelAlteracao Venda) {
        ModelMapper mapper = new ModelMapper();
        VendaDto dto = mapper.map(Venda, VendaDto.class);
        dto = service.atualizarVenda(id, dto);

        return new ResponseEntity<>(mapper.map(dto, VendaModelResponse.class), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> removerVenda(@PathVariable String id) {
        service.removerVenda(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value="/{id}")
    public ResponseEntity<Void> definirNaoVendido(@PathVariable String id) {
        if(service.definirComoNaoVendido(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
