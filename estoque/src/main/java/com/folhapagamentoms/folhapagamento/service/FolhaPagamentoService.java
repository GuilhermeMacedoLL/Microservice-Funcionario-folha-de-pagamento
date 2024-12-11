package com.folhapagamentoms.folhapagamento.service;

import java.util.List;

import com.folhapagamentoms.folhapagamento.dto.FolhaPagamentoDTO;
import com.folhapagamentoms.folhapagamento.entity.FolhaPagamento;

public interface FolhaPagamentoService {
	
	List<FolhaPagamento> getAll();
	
	FolhaPagamento getFolhaPagamento(Long id);
	
	FolhaPagamento create(FolhaPagamentoDTO dtoInput);
	
	FolhaPagamento update(FolhaPagamentoDTO dtoInput);
	
	void delete(Long id);

	List<FolhaPagamento> getFolha(String cpf);

}
