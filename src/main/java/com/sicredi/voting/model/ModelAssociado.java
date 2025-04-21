package com.sicredi.voting.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe que representa o modelo de um associado.
 * @author Yasmim Roeder
 */
@Data
@NoArgsConstructor
@Document(collection = "associado")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModelAssociado {

    @Id
    private String associadoId;

    private String nome;
    private String sobrenome;
    private String cpf;
}
