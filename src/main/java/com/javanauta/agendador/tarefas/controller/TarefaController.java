package com.javanauta.agendador.tarefas.controller;

import com.javanauta.agendador.tarefas.business.TarefaService;
import com.javanauta.agendador.tarefas.business.dto.TarefaDTO;
import com.javanauta.agendador.tarefas.infrastructure.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> gravarTarefas(@RequestHeader("Authorization") String authorization,
                                                   @RequestBody TarefaDTO tarefaDTO){
        return ResponseEntity.ok(tarefaService.gravarTarefa(authorization, tarefaDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){

        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));

    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscaListaDeTarefasPorEmail(@RequestHeader("Authorization") String authorization){
        return ResponseEntity.ok(tarefaService.buscarTarefasPorEmail(authorization));

    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id){
        tarefaService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefaDTO> alteraStatusNotificacao(@RequestParam("status")Status status,
                                                             @RequestParam("id") String id){
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> updateDeTarefas(@RequestBody TarefaDTO tarefaDTO,
                                                     @RequestParam("id") String id){
        return ResponseEntity.ok(tarefaService.updateDeTarefas(tarefaDTO, id));
    }
}
