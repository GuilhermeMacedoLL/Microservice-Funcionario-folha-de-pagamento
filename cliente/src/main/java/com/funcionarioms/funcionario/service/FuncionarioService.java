package com.funcionarioms.funcionario.service;

import java.util.List;

import com.funcionarioms.funcionario.dto.FuncionarioDTO;
import com.funcionarioms.funcionario.entity.Funcionario;

public interface FuncionarioService {
	
	List<Funcionario> getAll();
	
	Funcionario getFuncionarios(Long id);
	
	Funcionario create(FuncionarioDTO dtoInput);
	
	Funcionario update(FuncionarioDTO dtoInput);
	
	void delete(Long id);
	
	Funcionario getByCpf(String cpf);

}
