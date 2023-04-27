package com.aulaspring.exercicio.services;

import com.aulaspring.exercicio.models.Cliente;
import com.aulaspring.exercicio.models.Nivel;
import com.aulaspring.exercicio.models.Tarefa;
import com.aulaspring.exercicio.models.Usuario;
import com.aulaspring.exercicio.repositories.TarefaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    Logger logger = LoggerFactory.getLogger(TarefaService.class);


    // metodo para salvar objeto no banco de dados através do repository
    public Tarefa save(Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

    // outra forma de escrever o metodo para salvar
//    public Tarefa save(String descricao, Nivel nivel, boolean realizado){
//        Tarefa tarefa = new Tarefa();
//        tarefa.setDescricao(descricao);
//        tarefa.setNivel(nivel);
//        tarefa.setRealizado(realizado);
//        return tarefaRepository.save(tarefa);
//    }

    // metodo para buscar todos objetos (Lista) no banco de dados através do repository
    public List<Tarefa> findAll(){
        logger.info("Chamada de todas tarefas");
        return tarefaRepository.findAll();
    }

    // metodo para buscar objeto por id no banco de dados através do repository
    public Tarefa findById(Long id){
        logger.info("Chamada de tarefa por id -> com ID = " + id);
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        logger.warn("Exemplo de WARN");
        String retorno = tarefa.isPresent() ? tarefa.get().toString() : null;
        logger.info("Objeto retornado pela consulta " + retorno);
        return tarefa.isPresent() ? tarefa.get() : null;
    }

    // metodo para atualizar objeto no banco de dados através do repository
    public Tarefa update(Tarefa tarefa, Long id) {
        logger.info("Solicitado atualização da tarefa com id " + id);
        logger.error("Exemplo de log ERROR");
        logger.info("Objeto atualizado " + tarefa.toString() );
//        if (findById(id) != null){
//            return tarefaRepository.save(tarefa);
//        } else {
//            return null;
//        }
        // essa linha abaixo substitui as 5 anteriores
        return findById(id) != null ? tarefaRepository.save(tarefa) : null;

    }

    // metodo para excluir objeto no banco de dados através do repository
    public boolean deleteById(Long id) {
        logger.info("Solicitado deletar a tarefa com id " + id);
        if ( tarefaRepository.existsById(id) ) {
            logger.info("Localizado a tarefa com id " + id);
            tarefaRepository.deleteById(id);
            logger.info("Deletado a tarefa com id " + id);
            return true;
        }
        logger.info("Tarefa com id " + id + " não localizado");
        return false;
    }

    // metodo para listar tarefas de determinado usuario
    public List<Tarefa> findByResponsavel(Usuario usuario){
        return tarefaRepository.findByResponsavel(usuario);
    }

    // metodo para listar tarefas de determinado cliente
    public List<Tarefa> findByCliente(Cliente cliente){
        return tarefaRepository.findByCliente(cliente);
    }
}
