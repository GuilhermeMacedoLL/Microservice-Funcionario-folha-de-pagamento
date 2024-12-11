package com.funcionarioms.funcionario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Builder
@Getter
@AllArgsConstructor @NoArgsConstructor
public class FuncionarioDTO {
	
	private Long id;
	private String nome;
	private String sobreNome;
	private String cpf;
	private String salario;

}
