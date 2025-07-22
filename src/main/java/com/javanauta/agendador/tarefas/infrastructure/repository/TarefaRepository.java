package com.javanauta.agendador.tarefas.infrastructure.repository;

import com.javanauta.agendador.tarefas.infrastructure.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TarefaRepository extends MongoRepository<TarefaEntity, String>{

    List<TarefaEntity> findByDataEventoBetween(LocalDateTime dataIncial, LocalDateTime dataFinal);

    List<TarefaEntity> findByEmailUsuario(String token);
}
