package com.sicredi.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sicredi.voting.model.ModelAssociado;
import com.sicredi.voting.service.ServiceAssociado;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

/**
 * Controller para gerenciamento dos associados
 * @author Yasmim Roeder
 */
@RestController
@RequestMapping("/associado")
public class ControllerAssociado {

    @Autowired
    private ServiceAssociado serviceAssociado;

    @ApiOperation(value = "Cadastrar um novo associado")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Associado criado com sucesso"),
        @ApiResponse(code = 400, message = "Erro ao criar associado"),
    })
    @PostMapping ("/cadastrar")
    public String cadastrarAssociado(
        @RequestParam String nome, 
        @RequestParam String sobrenome, 
        @RequestParam String cpf) {
        return serviceAssociado.addAssociado(nome, sobrenome, cpf);
    }

    @ApiOperation(value = "Buscar um associado")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Associado encontrado"),
        @ApiResponse(code = 400, message = "Erro ao buscar associado"),
    })
    @GetMapping ("/{id}/search")
    public ModelAssociado getAssociado(@PathVariable String id) {
        return serviceAssociado.getAssociado(id);
    }

    @ApiOperation(value = "Atualizar um associado")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Associado atualizado com sucesso"),
        @ApiResponse(code = 400, message = "Erro ao atualizar associado"),
    })
    @PutMapping ("/{id}/atualizar")
    public ModelAssociado atualizarAssociado(
        @RequestParam String id, 
        @RequestParam String nome,
        @RequestParam String sobrenome,
        @RequestParam String cpf) {
        return serviceAssociado.updateAssociado(id, nome, sobrenome, cpf);
    }

    @ApiOperation(value = "Remover um associado")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Associado deletado com sucesso"),
        @ApiResponse(code = 400, message = "Erro ao deletar associado"),
    })
    @DeleteMapping ("/{id}/deletar")
    public String deletarAssociado(@PathVariable String id) {
        return serviceAssociado.deleteAssociado(id);
    }

    @ApiOperation(value = "Busca por todos os associados cadastrados")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de associados encontrada"),
        @ApiResponse(code = 400, message = "Erro ao listar associados"),
    })
    @GetMapping ("/listar")
    public List<ModelAssociado> listarAssociados() {
        return serviceAssociado.listarAssociados();
    }
}
