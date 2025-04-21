package com.sicredi.voting.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import javax.annotation.processing.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe que representa o modelo de uma pauta.
 * @author Yasmim Roeder
 */
@Data
@Builder
@Document(collection = "pauta")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModelPauta {

    @Id
    @Generated(value = "pautaId")
    private String pautaId;
    private String titulo;
    private String descricao;

    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private boolean votacaoAberta;
}
