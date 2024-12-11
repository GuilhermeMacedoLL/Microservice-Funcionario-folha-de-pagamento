package com.folhapagamentoms.folhapagamento.entity;

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
@Table(name = "FOLHA")
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class FolhaPagamento {
	
	@Id
	@Column(name = "ID_PAGAMENTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "SALARIO")
	private String salario;
	
	@Column(name = "CPF")
	private String cpf;
}
