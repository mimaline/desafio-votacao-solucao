package com.sicredi.voting.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sicredi.voting.model.ModelPauta;
import com.sicredi.voting.service.ServicePauta;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller para gerenciamento das pautas
 * @author Yasmim Roeder
 */
@RestController
@RequestMapping("/pauta")
public class ControllerPauta {

    @Autowired
    private ServicePauta servicePauta;

    @PostMapping("/criar")
    @ApiOperation(value = "Cadastrar uma nova pauta")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pauta criada com sucesso"),
        @ApiResponse(code = 400, message = "Erro ao criar pauta"),
    })
    public String addPauta(@RequestBody ModelPauta pauta) {
        return servicePauta.addPauta(pauta);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Buscar todas as pautas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pautas encontradas"),
        @ApiResponse(code = 400, message = "Erro ao buscar pautas"),
    })
    public List<ModelPauta> findAll() {
        return servicePauta.findAll();
    }

    @ApiOperation(value = "Apagar uma pauta")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pauta apagada com sucesso"),
        @ApiResponse(code = 400, message = "Erro ao apagar pauta"),
    })
    @DeleteMapping("/{id}/delete")
    public void deletePauta(@PathVariable String id) {
        servicePauta.delete(id);
    }

    @GetMapping("/{id}/search")
    @ApiOperation(value = "Buscar uma pauta")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pauta encontrada"),
        @ApiResponse(code = 400, message = "Erro ao buscar pauta"),
    })
    public String searchPauta(@PathVariable String id) {
        try {
            return servicePauta.searchPauta(id).toString();
        } catch (NoSuchElementException e) {
            return "ID nao encontrado";
        }
    }

    @PostMapping("/{id}/update")
    @ApiOperation(value = "Atualizar uma pauta")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pauta atualizada com sucesso"),
        @ApiResponse(code = 400, message = "Erro ao atualizar pauta"),
    })
    public String updatePauta(@RequestBody String id, ModelPauta updatedPauta) {
        return servicePauta.updatePauta(id, updatedPauta).toString();
    }

    @PostMapping("/{id}/fechar")
    @ApiOperation(value = "Fechar uma pauta")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pauta fechada com sucesso"),
        @ApiResponse(code = 400, message = "Erro ao fechar pauta"),
    })
    public String fecharPauta(String id) {
        return servicePauta.fecharPauta(id);
    }

    @PostMapping("/{id}/abrir")
    @ApiOperation(value = "Abrir uma pauta")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pauta aberta com sucesso"),
        @ApiResponse(code = 400, message = "Erro ao abrir pauta"),
    })
    public String abrirPauta(String id, Integer duracaoEmMinutos) {
        return servicePauta.abrirPauta(id, duracaoEmMinutos);
    }
}
