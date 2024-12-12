package com.listaTarefa.Tarefas.controller;

import com.listaTarefa.Tarefas.DTO.TarefaDto;
import com.listaTarefa.Tarefas.entity.Tarefa;
import com.listaTarefa.Tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<TarefaDto> getAllTarefas(){
        return tarefaService.getAllTarefas();
    }

    @PostMapping
    public ResponseEntity<TarefaDto> createTarefa(@RequestBody TarefaDto tarefaDto){
        TarefaDto createTarefa = tarefaService.createTarefa(tarefaDto);
        return ResponseEntity.ok(createTarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDto> updateTarefa(@PathVariable Long id, @RequestBody TarefaDto updatedTarefaDTO) {
        TarefaDto updateTarefa = tarefaService.updateTarefa(id, updatedTarefaDTO);
        return ResponseEntity.ok(updateTarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id) {
        tarefaService.deleteTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/prioridade")
    public ResponseEntity<List<TarefaDto>> searchTarefas(@RequestParam String prioridade) {

        List<TarefaDto> tarefas = tarefaService.searchTarefas(prioridade);
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/search/realizado")
    public ResponseEntity<List<TarefaDto>> searchRealizado(@RequestParam String realizado) {

        List<TarefaDto> realizados = tarefaService.searchRealizado(realizado);
        return ResponseEntity.ok(realizados);
    }
}
