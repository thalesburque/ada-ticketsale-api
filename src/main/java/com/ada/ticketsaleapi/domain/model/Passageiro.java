package com.ada.ticketsaleapi.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table("passageiro")
public class Passageiro {
	
	@EqualsAndHashCode.Include
	@Id
	private Long id;
	
	private String nome;
    private String email;
    private String telefone;

}
