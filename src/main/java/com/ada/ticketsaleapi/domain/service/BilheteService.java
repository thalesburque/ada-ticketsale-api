package com.ada.ticketsaleapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.ticketsaleapi.domain.exception.VooEsgotadoException;
import com.ada.ticketsaleapi.domain.model.Bilhete;
import com.ada.ticketsaleapi.domain.model.Voo;
import com.ada.ticketsaleapi.domain.repository.BilheteRepository;

import reactor.core.publisher.Mono;

@Service
public class BilheteService {
	
	@Autowired
	private BilheteRepository bilheteRepository;
	
	@Autowired
	private VooService vooService;
	
	
    public Mono<Bilhete> emitirBilhete(Bilhete bilhete){
    	
        Mono<Voo> vooMono = vooService.getVooById(bilhete.getVooId());

        Mono<Bilhete> bilheteMono =  vooMono.flatMap( voo -> {
        	
            if(voo.getTotalAssentoVendido() >= voo.getTotalAssento()) {
            	return Mono.error(new VooEsgotadoException("O voo esta esgotado"));
            }
            
            voo.setTotalAssentoVendido(voo.getTotalAssentoVendido() +1);
 
            Mono<Bilhete> bilheteEmitido =  bilheteRepository.save(bilhete);

            return bilheteEmitido.flatMap(b -> vooService.save(voo).thenReturn(b));
            
        }).onErrorResume(VooEsgotadoException.class, null);

      return bilheteMono;
    }


}
