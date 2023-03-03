package com.ada.ticketsaleapi.domain.model;

import lombok.Data;

@Data
public class Bilhete {
	
	private String numeroBilhete;
    private Passageiro passageiro;
    private Voo voo;

}
