package com.listaTarefa.Tarefas.service;

import com.listaTarefa.Tarefas.DTO.TarefaDto;
import com.listaTarefa.Tarefas.entity.Tarefa;
import com.listaTarefa.Tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    // Converte uma entidade Tarefa em TarefaDTO
    private TarefaDto convertToDTO(Tarefa tarefa){
        return new TarefaDto(tarefa.getId(), tarefa.getNome(), tarefa.getDescricao(), tarefa.getPrioridade(), tarefa.isRealizado());
    }

    // Converte um TarefaDTO em uma entidade Tarefa
    private Tarefa convertToEntity(TarefaDto tarefaDto){
        Tarefa tarefa = new Tarefa();
        tarefa.setNome(tarefaDto.getNome());
        tarefa.setDescricao(tarefaDto.getDescricao());
        tarefa.setPrioridade(tarefaDto.getPrioridade());
        tarefa.setRealizado(tarefa.isRealizado());
        return tarefa;
    }

    //lista do que pode ser digitado no campo PRIORIDADE
    private static final List<String> PRIORIDADES_VALIDAS = List.of("ALTA", "MEDIA", "BAIXA");

    private void validateTarefaDto(TarefaDto tarefaDto) {
        if (tarefaDto.getNome() == null || tarefaDto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo 'nome' não pode estar vazio.");
        }
        if (tarefaDto.getDescricao() == null || tarefaDto.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo 'descrição' não pode estar vazio.");
        }
        if (tarefaDto.getPrioridade() == null || tarefaDto.getPrioridade().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo 'prioridade' é obrigatório.");
        }
        String prioridadeMaiuscula = tarefaDto.getPrioridade().toUpperCase();
        if (!PRIORIDADES_VALIDAS.contains(prioridadeMaiuscula)) {
            throw new IllegalArgumentException("O valor da prioridade deve ser 'ALTA', 'MEDIA' ou 'BAIXA'. Por favor, digite em maiúsculas.");
        }
    }

    //cria
    public TarefaDto createTarefa(TarefaDto tarefaDto) {
        validateTarefaDto(tarefaDto);
        Tarefa tarefa = convertToEntity(tarefaDto);
        Tarefa savedTarefa = tarefaRepository.save(tarefa);
        return convertToDTO(savedTarefa);
    }

    //lista
    public List<TarefaDto> getAllTarefas() {
        return tarefaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //update
    public TarefaDto updateTarefa(Long id, TarefaDto updateTarefaDTO) {
        validateTarefaDto(updateTarefaDTO);

        return tarefaRepository.findById(id)
                .map(existingTarefa -> {
                    existingTarefa.setNome(updateTarefaDTO.getNome());
                    existingTarefa.setDescricao(updateTarefaDTO.getDescricao());
                    existingTarefa.setPrioridade(updateTarefaDTO.getPrioridade());
                    existingTarefa.setRealizado(updateTarefaDTO.isRealizado());
                    Tarefa savedTarefa = tarefaRepository.save(existingTarefa);
                    return convertToDTO(savedTarefa);
                })
                .orElse(null);
    }

    //delete
    public boolean deleteTarefa(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //procura por prioridade
    public List<TarefaDto> searchTarefas(String prioridade) {
        if (prioridade == null || prioridade.trim().isEmpty()) {
            throw new IllegalArgumentException("O parâmetro 'prioridade' não pode estar vazio.");
        }
        return tarefaRepository.findByPrioridadeContaining(prioridade).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //procura por realizado ou não
    public List<TarefaDto> searchRealizado(String realizado) {
        if (realizado == null || realizado.trim().isEmpty()) {
            throw new IllegalArgumentException("O parâmetro 'realizado' não pode estar vazio.");
        }

        Boolean realizadoBoolean;
        if ("true".equalsIgnoreCase(realizado)) {
            realizadoBoolean = true;
        } else if ("false".equalsIgnoreCase(realizado)) {
            realizadoBoolean = false;
        } else {
            throw new IllegalArgumentException("O valor de 'realizado' deve ser 'true' ou 'false'.");
        }

        return tarefaRepository.findByRealizado(realizadoBoolean).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
