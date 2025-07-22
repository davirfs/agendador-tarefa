package com.javanauta.agendador.tarefas.business;

import com.javanauta.agendador.tarefas.business.dto.TarefaDTO;
import com.javanauta.agendador.tarefas.business.mapper.TarefaMapper;
import com.javanauta.agendador.tarefas.business.mapper.TarefaUpdateMapper;
import com.javanauta.agendador.tarefas.infrastructure.entity.TarefaEntity;
import com.javanauta.agendador.tarefas.infrastructure.enums.Status;
import com.javanauta.agendador.tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.agendador.tarefas.infrastructure.repository.TarefaRepository;
import com.javanauta.agendador.tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final TarefaUpdateMapper tarefaUpdateMapper;
    private final JwtUtil jwtUtil;

    public TarefaDTO gravarTarefa(String token, TarefaDTO tarefaDTO){
        String email = jwtUtil.extractUsername(token.substring(7));

        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatus(Status.PENDENTE);
        tarefaDTO.setEmailUsuario(email);

        TarefaEntity tarefaEntity = tarefaMapper.paraTarefaEntity(tarefaDTO);
        return tarefaMapper.paraTarefaDTO(tarefaRepository.save(tarefaEntity));

    }

    public List<TarefaDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                           LocalDateTime dataFinal){
        return tarefaMapper.paraListaTarefaDTO(
                tarefaRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefaDTO> buscarTarefasPorEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));
        List<TarefaEntity> listaDeTarefas = tarefaRepository.findByEmailUsuario(email);
        return tarefaMapper.paraListaTarefaDTO(listaDeTarefas);
    }

    public void deletaTarefaPorId(String id){
        try {
            tarefaRepository.deleteById(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao tentar deletar id: " + id, e.getCause());
        }
    }

    public TarefaDTO alteraStatus(Status status, String id){
        try {
            TarefaEntity entity = tarefaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada."));
            entity.setStatus(status);
            return tarefaMapper.paraTarefaDTO(tarefaRepository.save(entity));
        }catch (ResourceNotFoundException e){
            throw new RuntimeException("Erro ao alterar status da tarefa", e.getCause());
        }
    }

    public TarefaDTO updateDeTarefas(TarefaDTO tarefaDTO, String id){
        try {
            TarefaEntity entity = tarefaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));
            tarefaUpdateMapper.updateDeTarefas(tarefaDTO, entity);
            return tarefaMapper.paraTarefaDTO(tarefaRepository.save(entity));
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa", e.getCause());
        }
    }
}
