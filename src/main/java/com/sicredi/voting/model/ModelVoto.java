package com.sicredi.voting.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que representa o modelo de um voto.
 * @author Yasmim Roeder
 */
@Data
@NoArgsConstructor
@Document(collection = "voto")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModelVoto {

    private ModelPauta pauta;
    private ModelAssociado associado;
    private boolean voto;
}
