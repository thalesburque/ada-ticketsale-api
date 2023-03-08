package com.ada.ticketsaleapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ada.ticketsaleapi.domain.exception.EntidadeNaoEncontradaException;
import com.ada.ticketsaleapi.domain.exception.VooEsgotadoException;
import com.ada.ticketsaleapi.domain.model.Bilhete;
import com.ada.ticketsaleapi.domain.model.Passageiro;
import com.ada.ticketsaleapi.domain.model.Voo;
import com.ada.ticketsaleapi.domain.repository.BilheteRepository;

import reactor.core.publisher.Mono;

@Service
public class BilheteService {

	@Autowired
	private BilheteRepository bilheteRepository;

	@Autowired
	private VooService vooService;

	@Autowired
	private PassageiroService passageiroService;

	@Transactional
	public Mono<Bilhete> emitirBilhete(Bilhete bilhete) {

		Mono<Voo> vooMono = vooService.getVooById(bilhete.getVooId());

		Mono<Passageiro> passageiroMono = passageiroService.getPassageiroById(bilhete.getPassageiroId());
		
		Mono<Bilhete> bilheteMono = Mono.zip(vooMono, passageiroMono).flatMap(zip -> {
			
			Voo voo = zip.getT1();

			if (voo.getTotalAssentoVendido() >= voo.getTotalAssento()) {
				return Mono.error(new VooEsgotadoException("O voo está esgotado"));
			} else {
				voo.setTotalAssentoVendido(voo.getTotalAssentoVendido() +1);
				bilhete.setNumeroBilhete(voo.getTotalAssentoVendido().toString());
			}
			
			Mono<Bilhete> bilheteEmitido = bilheteRepository.save(bilhete);
			
			return bilheteEmitido.flatMap(b -> vooService.save(voo).thenReturn(b));

		});

		return bilheteMono;

	}

	
	public Mono<Bilhete> getBilheteById(Long id) {

		return Mono.just(id).flatMap(bilheteRepository::findById)
				.switchIfEmpty(Mono.error(new EntidadeNaoEncontradaException("Passagem nao encontrada")));

	}

}
