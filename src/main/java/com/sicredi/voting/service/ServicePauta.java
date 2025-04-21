package com.sicredi.voting.service;

import java.util.List;

import com.sicredi.voting.model.ModelPauta;

/**
 * Interface que representa o serviço de pautas.
 * @author Yasmim Roeder
 */
public interface ServicePauta {

    List<ModelPauta> findAll();

    String addPauta(ModelPauta pauta);

    ModelPauta searchPauta(String id);

    void delete(String id);

    ModelPauta updatePauta(String id, ModelPauta updatedPauta);

    String fecharPauta(String id);

    String abrirPauta(String id, Integer duracaoEmMinutos);

    void fechaPautasAutomaticamente();
}
