package com.sicredi.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicredi.voting.service.ServiceVoto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Controller para gerenciamento das votações.
 * @author Yasmim Roeder
 */
@RestController
@RequestMapping("/votacao")
public class ControllerVoto {

    @Autowired
    ServiceVoto serviceVoto;

    @PostMapping("/votar")
    @ApiOperation(value = "Registrar um voto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Voto registrado com sucesso"),
        @ApiResponse(code = 400, message = "Erro ao cadastrar voto"),
    })
    public String cadastrarVoto(String idAssociado, String idPauta, boolean voto) {
        return serviceVoto.cadastrarVoto(idAssociado, idPauta, voto);
    }

    @GetMapping("/totalVotos")
    @ApiOperation(value = "Buscar o total de votos de uma pauta")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Total de votos encontrado"),
        @ApiResponse(code = 400, message = "Erro ao buscar total de votos"),
    })
    public String getTotalVotos(@RequestParam String idPauta) {
        return serviceVoto.getTotalVotos(idPauta);
    }

    @GetMapping("/resultado") 
    @ApiOperation(value = "Buscar o resultado de uma pauta")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Resultado encontrado"),
        @ApiResponse(code = 400, message = "Erro ao buscar resultado"),
    })
    public String getResultado(@RequestParam String idPauta) {
        return serviceVoto.getResultado(idPauta);
    }
}
