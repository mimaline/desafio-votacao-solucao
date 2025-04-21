package com.sicredi.voting.service;

/**
 * Interface que representa o serviço de votos.
 * @author Yasmim Roeder
 */
public interface ServiceVoto {

    String cadastrarVoto(String idAssociado, String idPauta, boolean voto);

    String getTotalVotos(String idPauta);

    String getResultado(String idPauta);

}
