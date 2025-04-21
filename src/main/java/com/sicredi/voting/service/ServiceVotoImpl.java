package com.sicredi.voting.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicredi.voting.model.ModelVoto;
import com.sicredi.voting.repository.RepositoryVoto;

/**
 * Implementação do serviço de votos.
 * @author Yasmim Roeder
 */
@Service
public class ServiceVotoImpl implements ServiceVoto {

    @Autowired
    private RepositoryVoto repositoryVoto;
    @Autowired
    private ServicePautaImpl servicePautaImpl;
    @Autowired
    private ServiceAssociadoImpl serviceAssociadoImpl;

    @Override
    public String cadastrarVoto(String idAssociado, String idPauta, boolean voto) {
        try {
            servicePautaImpl.searchPauta(idPauta); 
        } catch (NoSuchElementException e) {
            return "Pauta não encontrada";
        }
        try {
            serviceAssociadoImpl.getAssociado(idAssociado); 
        } catch (NoSuchElementException e) {
            return "Associado não encontrado";
        }
        if (!servicePautaImpl.searchPauta(idPauta).isVotacaoAberta()) {
            return "Votação encerrada";
        } 
        if (repositoryVoto.existsByAssociadoAndPauta(serviceAssociadoImpl.getAssociado(idAssociado), servicePautaImpl.searchPauta(idPauta))) {
            return "Voto já registrado para este associado e pauta";
        } else {
            ModelVoto voto1 = new ModelVoto();
            voto1.setAssociado(serviceAssociadoImpl.getAssociado(idAssociado));
            voto1.setPauta(servicePautaImpl.searchPauta(idPauta));
            voto1.setVoto(voto);
            repositoryVoto.save(voto1);
            return "Voto registrado com sucesso";
        }
    }

    @Override
    public String getTotalVotos(String idPauta) {
        try {
            servicePautaImpl.searchPauta(idPauta); 
            return "Total de votos: " + repositoryVoto.countByPauta(servicePautaImpl.searchPauta(idPauta));
        } catch (NoSuchElementException e) {
            return "Pauta não encontrada";
        }
    }

    @Override
    public String getResultado(String idPauta) {
        try {
            servicePautaImpl.searchPauta(idPauta);
            if (!servicePautaImpl.searchPauta(idPauta).isVotacaoAberta()) {
                int votosAFavor = repositoryVoto.countByPautaAndVoto(servicePautaImpl.searchPauta(idPauta), true);
                int votosContra = repositoryVoto.countByPautaAndVoto(servicePautaImpl.searchPauta(idPauta), false);
                String resultado =  (votosAFavor > votosContra) ? "aprovada" : "rejeitada";
                    return "Pauta " + resultado;
            } else {
                return "Votação ainda em andamento";
            }
        } catch (NoSuchElementException e) {
            return "Pauta não encontrada";
        }
    }
}
