package com.sicredi.voting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sicredi.voting.model.ModelPauta;

import java.util.List;

/**
 * Interface que representa o repositório de pautas.
 * @author Yasmim Roeder
 */
@Repository
public interface RepositoryPauta extends MongoRepository<ModelPauta,String> {

    List<ModelPauta> findAll();
}
