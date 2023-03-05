package com.ada.ticketsaleapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ada.ticketsaleapi.domain.exception.EntidadeNaoEncontradaException;
import com.ada.ticketsaleapi.domain.model.Voo;
import com.ada.ticketsaleapi.domain.repository.VooRepository;

import reactor.core.publisher.Mono;

@Service
public class VooService {

	@Autowired
	private VooRepository vooRepository;
	
	@Transactional
	public Mono<Voo> save(Voo voo) {
		return vooRepository.save(voo);
	}

	public Mono<Voo> getVooById(Long id) {

		return Mono.just(id)
				.flatMap(vooRepository::findById)
				.switchIfEmpty(Mono.error(new EntidadeNaoEncontradaException("Voo nao encontrado")));

	}

}
