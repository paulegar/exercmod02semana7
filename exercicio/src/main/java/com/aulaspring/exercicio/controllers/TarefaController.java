package com.aulaspring.exercicio.controllers;

import com.aulaspring.exercicio.models.Cliente;
import com.aulaspring.exercicio.models.Tarefa;
import com.aulaspring.exercicio.models.Usuario;
import com.aulaspring.exercicio.services.ClienteService;
import com.aulaspring.exercicio.services.TarefaService;
import com.aulaspring.exercicio.services.UsuarioService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    // metodo para salvar objeto no através do service
    @PostMapping
    public ResponseEntity<Tarefa> save(@RequestBody Tarefa tarefa){
        // formas de enviar o código de status 201 created quando gravou
//        return ResponseEntity.status(201).body(tarefaService.save(tarefa));
//        return ResponseEntity.created(null).body(tarefaService.save(tarefa));
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.save(tarefa));
    }

    // metodo para buscar todas tarefas
    @GetMapping
    public ResponseEntity<List<Tarefa>> findAll(){
        return ResponseEntity.ok().body(tarefaService.findAll());
    }

    @GetMapping(value = "/tarefasporusuario")
    public ResponseEntity<List<Tarefa>> findByResponsavel(
            @RequestBody Usuario usuario
            ){
        return ResponseEntity.ok().body(tarefaService.findByResponsavel(usuario));
    }

    @GetMapping(value = "/tarefasporusuario/{idusuario}")
    public ResponseEntity<List<Tarefa>> findByResponsavel(@PathVariable(name = "idusuario") Long idUsuario){
        Usuario usuario = usuarioService.findById(idUsuario);
        if ( usuario != null ) {
            return ResponseEntity.ok().body(tarefaService.findByResponsavel(usuario));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/tarefasporcliente/{idcliente}")
    public ResponseEntity<List<Tarefa>> findByCliente(@PathVariable(name = "idcliente") Long idCliente){
        Cliente cliente = clienteService.findById(idCliente);
        if ( cliente != null ) {
            return ResponseEntity.ok().body(tarefaService.findByCliente(cliente));
        }
        return ResponseEntity.noContent().build();
    }

    // metodo para buscar tarefa por ID
    @GetMapping(value = "/tarefaporid/{id}")
    public ResponseEntity<Tarefa> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(tarefaService.findById(id));
    }

    // metodo para atualizar uma tarefa por ID passando objeto atualizado
    @PutMapping(value = "/atualizarporid/{id}")
    public ResponseEntity<Tarefa> updateById(
            @PathVariable(name = "id") Long id,
            @RequestBody Tarefa tarefa ) {
        return ResponseEntity.ok().body(tarefaService.update(tarefa, id));
    }

    @DeleteMapping(value = "/deletarporid/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id){
        boolean deletadaComSucesso = tarefaService.deleteById(id);
        if ( deletadaComSucesso ) {
            // vai retornar quando deletou
            return ResponseEntity.ok().body("Deletado com sucesso");
        } else {
            // vai retornar quando não deletou
            return ResponseEntity.status(204).body("Não encontrado registro com id " + id);
        }
    }


}
