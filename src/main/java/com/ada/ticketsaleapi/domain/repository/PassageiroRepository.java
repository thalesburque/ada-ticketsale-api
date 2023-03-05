package com.ada.ticketsaleapi.domain.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.ada.ticketsaleapi.domain.model.Passageiro;

@Repository
public interface PassageiroRepository extends ReactiveCrudRepository<Passageiro, Long>{

}
