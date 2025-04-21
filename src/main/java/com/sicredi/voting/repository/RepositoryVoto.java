package com.sicredi.voting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sicredi.voting.model.ModelAssociado;
import com.sicredi.voting.model.ModelPauta;
import com.sicredi.voting.model.ModelVoto;

/**
 * Interface que representa o repositório de votos.
 * @author Yasmim Roeder
 */
public interface RepositoryVoto extends MongoRepository<ModelVoto, String> {

    boolean existsByAssociadoAndPauta(ModelAssociado associado, ModelPauta pauta);

    int countByPautaAndVoto(ModelPauta searchPauta, boolean b);
    
    int countByPauta(ModelPauta searchPauta);

}
