package com.javanauta.agendador.tarefas.controller;

import com.javanauta.agendador.tarefas.business.TarefaService;
import com.javanauta.agendador.tarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
