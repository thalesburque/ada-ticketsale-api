package com.ada.ticketsaleapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.ticketsaleapi.domain.exception.EntidadeNaoEncontradaException;
import com.ada.ticketsaleapi.domain.model.Passageiro;
import com.ada.ticketsaleapi.domain.repository.PassageiroRepository;

import reactor.core.publisher.Mono;

@Service
public class PassageiroService {
	
	@Autowired
	private PassageiroRepository passageiroRepository;
	
	public Mono<Passageiro> getPassageiroById(Long id) {

		return Mono.just(id)
				.flatMap(passageiroRepository::findById)
				.switchIfEmpty(Mono.error(new EntidadeNaoEncontradaException("Passageiro nao encontrado")));

	}

}
