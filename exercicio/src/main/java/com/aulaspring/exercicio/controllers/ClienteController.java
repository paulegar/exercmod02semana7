package com.aulaspring.exercicio.controllers;

import com.aulaspring.exercicio.models.Cliente;
import com.aulaspring.exercicio.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // metodo para salvar objeto no através do service
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        // formas de enviar o código de status 201 created quando gravou
//        return ResponseEntity.status(201).body(clienteService.save(cliente));
//        return ResponseEntity.created(null).body(clienteService.save(cliente));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
    }

    // metodo para buscar todas clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    // metodo para buscar cliente por ID
    @GetMapping(value = "/clienteporid/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(clienteService.findById(id));
    }

    // metodo para atualizar uma cliente por ID passando objeto atualizado
    @PutMapping(value = "/atualizarporid/{id}")
    public ResponseEntity<Cliente> updateById(
            @PathVariable(name = "id") Long id,
            @RequestBody Cliente cliente ) {
        return ResponseEntity.ok().body(clienteService.update(cliente, id));
    }

    @DeleteMapping(value = "/deletarporid/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id){
        boolean deletadaComSucesso = clienteService.deleteById(id);
        if ( deletadaComSucesso ) {
            // vai retornar quando deletou
            return ResponseEntity.ok().body("Deletado com sucesso");
        } else {
            // vai retornar quando não deletou
            return ResponseEntity.status(200).body("Não encontrado registro com id " + id);
        }
    }


}
