package com.emi.sprint1java.controller;

import com.emi.sprint1java.exceptions.ClientNotFoundException;
import com.emi.sprint1java.model.Cliente;
import com.emi.sprint1java.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Cliente")
public class ClienteController {

    @Autowired
    ClienteService service;

    // GET
    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar cliente por ID",
            description = "Retorna pesquisa de cliente por ID")
    @ApiResponse(responseCode = "200", description = "Cadastro do cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    public Cliente buscarId(@PathVariable int id) {
        Cliente cliente = service.getCliente(id);
        if (cliente != null) {
            return cliente;
        } else {
            throw new ClientNotFoundException(id);
        }
    }

    // POST
    @PostMapping
    @Operation(
            summary = "Salvar cadastro cliente",
            description = "Salva cadastro do cliente")
    @ApiResponse(responseCode = "201", description = "Cadastro do cliente salvo")
    @ApiResponse(responseCode = "400", description = "Erro ao salvar cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente) {
        return service.setCliente(cliente);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar cadastro do cliente",
            description = "Deleta o cadastro do cliente")
    @ApiResponse(responseCode = "200", description = "Cadastro do cliente deletado")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    public void deletar(@PathVariable int id) {
        Cliente cliente = service.getCliente(id);
        if (cliente != null) {
            service.deleteCliente(id);
        } else {
            throw new ClientNotFoundException(id);
        }
    }

    // PUT
    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar cadastro do cliente",
            description = "Atualiza cadastro do cliente")
    @ApiResponse(responseCode = "201", description = "Cadastro do cliente atualizado")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente atualizar(@PathVariable int id, @RequestBody Cliente cliente) {
        Cliente existente = service.getCliente(id);
        if (existente != null) {
            cliente.setId(id);
            return service.save(cliente);
        } else {
            throw new ClientNotFoundException(id);
        }
    }
}
