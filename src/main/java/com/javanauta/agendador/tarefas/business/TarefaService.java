package com.javanauta.agendador.tarefas.business;

import com.javanauta.agendador.tarefas.business.dto.TarefaDTO;
import com.javanauta.agendador.tarefas.business.mapper.TarefaMapper;
import com.javanauta.agendador.tarefas.infrastructure.entity.TarefaEntity;
import com.javanauta.agendador.tarefas.infrastructure.enums.Status;
import com.javanauta.agendador.tarefas.infrastructure.repository.TarefaRepository;
import com.javanauta.agendador.tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final JwtUtil jwtUtil;

    public TarefaDTO gravarTarefa(String token, TarefaDTO tarefaDTO){
        String email = jwtUtil.extractUsername(token.substring(7));

        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatus(Status.PENDENTE);
        tarefaDTO.setEmailUsuario(email);

        TarefaEntity tarefaEntity = tarefaMapper.paraTarefaEntity(tarefaDTO);
        return tarefaMapper.paraTarefaDTO(tarefaRepository.save(tarefaEntity));

    }
}
