package com.aulaspring.exercicio.services;

import com.aulaspring.exercicio.models.Usuario;
import com.aulaspring.exercicio.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

        @Autowired
        private UsuarioRepository usuarioRepository;

        Logger logger = LoggerFactory.getLogger(UsuarioService.class);


        // metodo para salvar objeto no banco de dados através do repository
        public Usuario save (Usuario usuario){
            return usuarioRepository.save(usuario);
        }

        // outra forma de escrever o metodo para salvar
//    public Usuario save(String descricao, Nivel nivel, boolean realizado){
//        Usuario usuario = new Usuario();
//        usuario.setDescricao(descricao);
//        usuario.setNivel(nivel);
//        usuario.setRealizado(realizado);
//        return usuarioRepository.save(usuario);
//    }

        // metodo para buscar todos objetos (Lista) no banco de dados através do repository
        public List<Usuario> findAll () {
        logger.info("Chamada de todas usuarios");
        return usuarioRepository.findAll();
    }

        // metodo para buscar objeto por id no banco de dados através do repository
        public Usuario findById (Long id){
        logger.info("Chamada de usuario por id -> com ID = " + id);
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        logger.warn("Exemplo de WARN");
        String retorno = usuario.isPresent() ? usuario.get().toString() : null;
        logger.info("Objeto retornado pela consulta " + retorno);
        return usuario.isPresent() ? usuario.get() : null;
    }

        // metodo para atualizar objeto no banco de dados através do repository
        public Usuario update (Usuario usuario, Long id){
        logger.info("Solicitado atualização da usuario com id " + id);
        logger.error("Exemplo de log ERROR");
        logger.info("Objeto atualizado " + usuario.toString());
//        if (findById(id) != null){
//            return usuarioRepository.save(usuario);
//        } else {
//            return null;
//        }
        // essa linha abaixo substitui as 5 anteriores
        return findById(id) != null ? usuarioRepository.save(usuario) : null;

    }

        // metodo para excluir objeto no banco de dados através do repository
        public boolean deleteById (Long id){
        logger.info("Solicitado deletar a usuario com id " + id);
        if (usuarioRepository.existsById(id)) {
            logger.info("Localizado a usuario com id " + id);
            usuarioRepository.deleteById(id);
            logger.info("Deletado a usuario com id " + id);
            return true;
        }
        logger.info("Usuario com id " + id + " não localizado");
        return false;
    }

}