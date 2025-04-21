package com.sicredi.voting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sicredi.voting.model.ModelAssociado;

/**
 * Interface que representa o repositório de associados.
 * @author Yasmim Roeder
 */
@Repository
public interface RepositoryAssociado extends MongoRepository<ModelAssociado, String> {

}
