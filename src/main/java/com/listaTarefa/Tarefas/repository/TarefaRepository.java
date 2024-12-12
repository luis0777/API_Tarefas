package com.listaTarefa.Tarefas.repository;

import com.listaTarefa.Tarefas.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByPrioridadeContaining(String partialPrioridade);
    List<Tarefa> findByRealizado(Boolean realizado);

}
