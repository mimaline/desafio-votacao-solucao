package com.sicredi.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sicredi.voting.model.ModelAssociado;
import com.sicredi.voting.repository.RepositoryAssociado;

import java.util.List;

/**
 * Implementação do serviço de associados.
 * @author Yasmim Roeder
 */
@Service
public class ServiceAssociadoImpl implements ServiceAssociado{

    @Autowired
    private RepositoryAssociado repositoryAssociado;

    @Override
    public String addAssociado(String nome, String sobrenome, String cpf) {
        ModelAssociado associado = new ModelAssociado();
        associado.setNome(nome);
        associado.setSobrenome(sobrenome);
        associado.setCpf(cpf);
        repositoryAssociado.save(associado).getAssociadoId();
        if (repositoryAssociado.existsById(associado.getAssociadoId())) {
            return "Associado cadastrado com sucesso com o id: " + associado.getAssociadoId();
        } else {
            return  "Erro ao cadastrar associado";
        }
    }

    @Override
    public ModelAssociado getAssociado(String id) {
        return repositoryAssociado.findById(id).get();
    }

    @Override
    public ModelAssociado updateAssociado(String id, String nome, String sobrenome, String cpf) {
        if (repositoryAssociado.existsById(id)) {
            ModelAssociado existingPauta = repositoryAssociado.findById(id).get();  
            ModelAssociado associado = existingPauta;
            associado.setNome(nome);
            associado.setSobrenome(sobrenome);
            associado.setCpf(cpf);
            return repositoryAssociado.save(associado);
        } else {
            throw new RuntimeException("Associado nao encontrado com o id: " + id);
        }
    }

    @Override
    public String deleteAssociado(String id) {
        if (!repositoryAssociado.existsById(id)) {
            return "Associado nao encontrado com o id: " + id;
        }
        repositoryAssociado.deleteById(id);
        if (repositoryAssociado.existsById(id)) {
            return "Erro ao deletar associado com o id: " + id;
        } else {
            return "Associado deletado com sucesso com o id: " + id;
        }
    }

    @Override
    public List<ModelAssociado> listarAssociados() {
        return repositoryAssociado.findAll();
    }
}
