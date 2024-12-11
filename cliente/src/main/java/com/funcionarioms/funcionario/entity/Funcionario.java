package com.funcionarioms.funcionario.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Builder
@Table(name = "FUNCIONARIOS")
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class Funcionario {
	
	@Id
	@Column(name = "ID_FUNCIONARIO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "SOBRENOME")
	private String sobreNome;
	
	@Column(name = "CPF")
	private String cpf;
	

}
