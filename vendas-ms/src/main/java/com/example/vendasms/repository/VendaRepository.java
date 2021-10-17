package com.example.vendasms.repository;

import java.util.List;

import com.example.vendasms.model.Venda;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends MongoRepository<Venda, String> {
    
    List<Venda> findByProduto(String produto);
}
