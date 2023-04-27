package com.aulaspring.exercicio.controllers;

import com.aulaspring.exercicio.models.Usuario;
import com.aulaspring.exercicio.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // metodo para salvar objeto no através do service
    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        // formas de enviar o código de status 201 created quando gravou
//        return ResponseEntity.status(201).body(usuarioService.save(usuario));
//        return ResponseEntity.created(null).body(usuarioService.save(usuario));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    // metodo para buscar todas usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.ok().body(usuarioService.findAll());
    }

    // metodo para buscar usuario por ID
    @GetMapping(value = "/usuarioporid/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(usuarioService.findById(id));
    }

    // metodo para atualizar uma usuario por ID passando objeto atualizado
    @PutMapping(value = "/atualizarporid/{id}")
    public ResponseEntity<Usuario> updateById(
            @PathVariable(name = "id") Long id,
            @RequestBody Usuario usuario ) {
        return ResponseEntity.ok().body(usuarioService.update(usuario, id));
    }

    @DeleteMapping(value = "/deletarporid/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id){
        boolean deletadaComSucesso = usuarioService.deleteById(id);
        if ( deletadaComSucesso ) {
            // vai retornar quando deletou
            return ResponseEntity.ok().body("Deletado com sucesso");
        } else {
            // vai retornar quando não deletou
            return ResponseEntity.status(200).body("Não encontrado registro com id " + id);
        }
    }


}
