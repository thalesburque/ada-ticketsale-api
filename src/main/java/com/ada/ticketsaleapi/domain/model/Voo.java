package com.ada.ticketsaleapi.domain.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Voo {
	
	private String numeroVoo;
    private String codigoAeroportoPartida;
    private String codigoAeroportoChegada;
    private LocalDateTime dataHoraPartida;
    private LocalDateTime dataHoraChegada;
    private Double preco;

}
