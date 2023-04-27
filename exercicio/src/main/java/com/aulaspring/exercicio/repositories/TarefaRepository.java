package com.aulaspring.exercicio.repositories;

import com.aulaspring.exercicio.models.Cliente;
import com.aulaspring.exercicio.models.Tarefa;
import com.aulaspring.exercicio.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    public List<Tarefa> findByResponsavel(Usuario usuario);

    public List<Tarefa> findByCliente(Cliente cliente);
}
