package com.javanauta.agendador.tarefas.infrastructure.repository;

import com.javanauta.agendador.tarefas.infrastructure.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TarefaRepository extends MongoRepository<TarefaEntity, String>{
}
