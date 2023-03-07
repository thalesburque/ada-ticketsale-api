package com.ada.ticketsaleapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ada.ticketsaleapi.domain.model.Bilhete;
import com.ada.ticketsaleapi.domain.service.BilheteService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bilhetes")
public class BilheteController {
	
	@Autowired
	private BilheteService bilheteService;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Bilhete> emitir(@RequestBody Bilhete bilhete) {
		return bilheteService.emitirBilhete(bilhete);
	}
	
	@GetMapping("/{bilheteId}")
	public Mono<Bilhete> consultar(@PathVariable Long bilheteId) {
		return bilheteService.getBilheteById(bilheteId);
	}

}
