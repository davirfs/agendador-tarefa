package com.javanauta.agendador.tarefas.business.mapper;

import com.javanauta.agendador.tarefas.business.dto.TarefaDTO;
import com.javanauta.agendador.tarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateMapper {

    void updateDeTarefas(TarefaDTO tarefaDTO, @MappingTarget TarefaEntity tarefaEntity);
}
