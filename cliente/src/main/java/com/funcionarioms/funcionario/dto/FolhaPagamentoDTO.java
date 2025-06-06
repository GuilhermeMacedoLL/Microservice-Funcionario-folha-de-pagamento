package com.funcionarioms.funcionario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class FolhaPagamentoDTO {

	private Long id;
	private String salario;
	private String cpf;
}
