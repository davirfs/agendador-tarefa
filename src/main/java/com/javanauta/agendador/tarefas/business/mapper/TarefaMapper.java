package com.javanauta.agendador.tarefas.business.mapper;

import com.javanauta.agendador.tarefas.business.dto.TarefaDTO;
import com.javanauta.agendador.tarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    @Mapping(source = "id", target = "id")
    TarefaEntity paraTarefaEntity(TarefaDTO tarefaDTO);
    TarefaDTO paraTarefaDTO(TarefaEntity tarefaEntity);
    List<TarefaEntity> paraListaTarefaEntity(List<TarefaDTO> tarefasDTO);
    List<TarefaDTO> paraListaTarefaDTO(List<TarefaEntity> tarefasEmtity);
}
