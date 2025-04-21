package com.sicredi.voting.service;

import java.util.List;
import com.sicredi.voting.model.ModelAssociado;

/**
 * Interface que representa o serviço de associados.
 * @author Yasmim Roeder
 */
public interface ServiceAssociado {

    String addAssociado(String nome, String sobrenome, String cpf);

    ModelAssociado getAssociado(String id);

    ModelAssociado updateAssociado(String id, String nome, String sobrenome, String cpf);

    String deleteAssociado(String id);

    List<ModelAssociado> listarAssociados();

}
