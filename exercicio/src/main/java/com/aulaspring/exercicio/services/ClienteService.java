package com.aulaspring.exercicio.services;

import com.aulaspring.exercicio.models.Cliente;
import com.aulaspring.exercicio.repositories.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

        @Autowired
        private ClienteRepository clienteRepository;

        Logger logger = LoggerFactory.getLogger(ClienteService.class);


        // metodo para salvar objeto no banco de dados através do repository
        public Cliente save (Cliente cliente){
            return clienteRepository.save(cliente);
        }

        // outra forma de escrever o metodo para salvar
//    public Cliente save(String descricao, Nivel nivel, boolean realizado){
//        Cliente cliente = new Cliente();
//        cliente.setDescricao(descricao);
//        cliente.setNivel(nivel);
//        cliente.setRealizado(realizado);
//        return clienteRepository.save(cliente);
//    }

        // metodo para buscar todos objetos (Lista) no banco de dados através do repository
        public List<Cliente> findAll () {
        logger.info("Chamada de todas clientes");
        return clienteRepository.findAll();
    }

        // metodo para buscar objeto por id no banco de dados através do repository
        public Cliente findById (Long id){
        logger.info("Chamada de cliente por id -> com ID = " + id);
        Optional<Cliente> cliente = clienteRepository.findById(id);
        logger.warn("Exemplo de WARN");
        String retorno = cliente.isPresent() ? cliente.get().toString() : null;
        logger.info("Objeto retornado pela consulta " + retorno);
        return cliente.isPresent() ? cliente.get() : null;
    }

        // metodo para atualizar objeto no banco de dados através do repository
        public Cliente update (Cliente cliente, Long id){
        logger.info("Solicitado atualização da cliente com id " + id);
        logger.error("Exemplo de log ERROR");
        logger.info("Objeto atualizado " + cliente.toString());
//        if (findById(id) != null){
//            return clienteRepository.save(cliente);
//        } else {
//            return null;
//        }
        // essa linha abaixo substitui as 5 anteriores
        return findById(id) != null ? clienteRepository.save(cliente) : null;

    }

        // metodo para excluir objeto no banco de dados através do repository
        public boolean deleteById (Long id){
        logger.info("Solicitado deletar a cliente com id " + id);
        if (clienteRepository.existsById(id)) {
            logger.info("Localizado a cliente com id " + id);
            clienteRepository.deleteById(id);
            logger.info("Deletado a cliente com id " + id);
            return true;
        }
        logger.info("Cliente com id " + id + " não localizado");
        return false;
    }

}