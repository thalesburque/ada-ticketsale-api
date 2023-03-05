package com.ada.ticketsaleapi.domain.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table("voo")
public class Voo {
	
	@EqualsAndHashCode.Include
	@Id
	private Long id;
	
	@Column("numero_voo")
	private String numeroVoo;
	
	@Column("codigo_aeroporto_partida")
    private String codigoAeroportoPartida;
	
	@Column("codigo_aeroporto_chegada")
    private String codigoAeroportoChegada;
	
	@Column("data_hora_partida")
    private LocalDateTime dataHoraPartida;
	
	@Column("data_hora_chegada")
    private LocalDateTime dataHoraChegada;
	
	@Column("total_assento")
	private Integer totalAssento;
	
	@Column("total_assento_vendido")
	private Integer totalAssentoVendido;
	
    private Double preco;
    
    

}
