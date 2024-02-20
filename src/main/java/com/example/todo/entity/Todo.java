package com.example.todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "todos")
@Getter
@Setter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotBlank
    private Integer prioridade;
    @NotBlank
    private String dataCriacao;
    private String dataFinalizacao;
    private boolean realizado;


    public Todo(String nome, String descricao, Integer prioridade, String dataCriacao, String dataFinalizacao, boolean realizado) {
        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.dataCriacao = dataCriacao;
        this.dataFinalizacao = dataFinalizacao;
        this.realizado = realizado;
    }

    public Todo() {

    }
}
