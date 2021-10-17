package com.example.vendasms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Venda {
    @Id
    private String id;
    private Integer codigo;
    private Integer quant;
    private String data;
    private Boolean vendido;
    
    public Venda(){
        setVendido(true);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getQuant() {
        return quant;
    }

    public void setQuant(Integer quant) {
        this.quant = quant;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public Boolean getVendido(){
        return vendido;
    }

    public void setVendido(Boolean vendido){
        this.vendido = vendido;
    }
}
