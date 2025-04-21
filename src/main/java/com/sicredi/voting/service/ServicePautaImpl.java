package com.sicredi.voting.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sicredi.voting.model.ModelPauta;
import com.sicredi.voting.repository.RepositoryPauta;

/**
 * Implementação do serviço de pautas.
 * @author Yasmim Roeder
 */
@Service
public class ServicePautaImpl implements ServicePauta{

    @Autowired
    private RepositoryPauta repositoryPauta;

    @Override
    public String addPauta(ModelPauta pauta) {
        return repositoryPauta.save(pauta).getPautaId();
    }
    
    @Override
    public List<ModelPauta> findAll() {
        return repositoryPauta.findAll();
    }


    @Override
    public void delete(String id) {
        repositoryPauta.deleteById(id);
    }

    @Override
    public ModelPauta searchPauta(String id) {
        return repositoryPauta.findById(id).get();
    }

    @Override
    public ModelPauta updatePauta(String id, ModelPauta updatedPauta) {
        if (repositoryPauta.existsById(id)) {
            ModelPauta existingPauta = repositoryPauta.findById(id).get();  
            ModelPauta pauta = existingPauta;
            pauta.setTitulo(updatedPauta.getTitulo());
            pauta.setDescricao(updatedPauta.getDescricao()); 
            pauta.setVotacaoAberta(updatedPauta.isVotacaoAberta());
            return repositoryPauta.save(pauta);
        } else {
            throw new RuntimeException("Pauta not found with id: " + id);
        }
    }

    @Override
    public String fecharPauta(String id) {
        try {
            ModelPauta pauta = searchPauta(id);
            if (pauta.isVotacaoAberta()) {
                pauta.setVotacaoAberta(false);
                repositoryPauta.save(pauta);
                return "Votação encerrada com sucesso";
            } else {
                return "Votacao já está fechada";
            }
        } catch (Exception e) {
            return "Pauta não encontrada";
        }
    }

    @Override
    public String abrirPauta(String id, Integer duracaoEmMinutos) {
        try {
            ModelPauta pauta = searchPauta(id);
            if (!pauta.isVotacaoAberta()) {
                LocalDateTime horaAtual = LocalDateTime.now();
                pauta.setDataHoraFim(horaAtual.plusMinutes(duracaoEmMinutos != null ? duracaoEmMinutos : 1));
                pauta.setVotacaoAberta(true);
                repositoryPauta.save(pauta);
                return "Votação aberta com sucesso";
            } else {
                return "Votacao já está aberta";
            }
        } catch (Exception e) {
            return "Pauta não encontrada";
        }
    }

    @Override
    @Scheduled(fixedRate = 60000) // Executa a cada 1 minuto
    public void fechaPautasAutomaticamente() {
        LocalDateTime horaAtual = LocalDateTime.now();
        List<ModelPauta> pautas = repositoryPauta.findAll();
        for (ModelPauta pauta : pautas) {
            if (pauta.isVotacaoAberta() && pauta.getDataHoraFim().isBefore(horaAtual)) {
                pauta.setVotacaoAberta(false);
                repositoryPauta.save(pauta);
                System.out.println("Votação encerrada automaticamente para a pauta: " + pauta.getTitulo());
            }
        }
    }
}
