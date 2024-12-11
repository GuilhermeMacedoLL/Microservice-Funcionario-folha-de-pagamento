package com.funcionarioms.funcionario.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcionarioms.funcionario.dto.FuncionarioDTO;
import com.funcionarioms.funcionario.entity.Funcionario;
import com.funcionarioms.funcionario.exception.AplicacaoException;
import com.funcionarioms.funcionario.mapper.FuncionarioMapper;
import com.funcionarioms.funcionario.repository.FuncionarioRepository;
import com.funcionarioms.funcionario.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	@Autowired
	private FuncionarioRepository repository;

	@Autowired
	private FuncionarioMapper mapper;

	@Override
	public List<Funcionario> getAll() {
		List<Funcionario> listaDados = repository.findAll();

		if (listaDados == null) {
			return new ArrayList<>();
		}
		return listaDados;
	}

	@Override
	public Funcionario getFuncionarios(Long id) {
		Optional<Funcionario> dado = repository.findById(id);

		if (!dado.isPresent()) {
			return new Funcionario();
		}
		return dado.get();
	}

	@Override
	public Funcionario create(FuncionarioDTO dto) {
	        Funcionario dado = repository.getFuncionarios(dto.getNome());

	        if (dado != null) {
	            throw new AplicacaoException("Funcionario já existe.");
	        }

	        dado = mapper.convertDtoToEntity(dto);
	        
	        return repository.save(dado);
	    }

	@Override
	public Funcionario update(FuncionarioDTO dto) {
		Funcionario dadoAtual = repository.getFuncionarios(dto.getNome());
		
		if (dadoAtual == null) {
			return new Funcionario();
		}
		
		Funcionario dadoNovo = mapper.convertDtoToEntity(dto);
		
		dadoNovo = this.validaCamposUpdate(dadoAtual, dadoNovo);
		
		return repository.save(dadoNovo);
	}
	
	public Funcionario validaCamposUpdate(Funcionario dadoAtual, Funcionario dadoNovo) {
		
		dadoAtual.setNome((dadoAtual.getNome().equals(dadoNovo.getNome()) ? dadoAtual.getNome() : dadoNovo.getNome()));
		dadoAtual.setSobreNome((dadoAtual.getSobreNome().equals(dadoNovo.getSobreNome()) ? dadoAtual.getSobreNome() : dadoNovo.getSobreNome()));
		dadoAtual.setCpf((dadoAtual.getCpf().equals(dadoNovo.getCpf()) ? dadoAtual.getCpf() : dadoNovo.getCpf()));
		
		
		return dadoAtual;
	}

	@Override
	public void delete(Long id) {
		Optional<Funcionario> dado = repository.findById(id);
		
		if (!dado.isPresent()) {
			throw new AplicacaoException("Funcionarios não existe.");
		}
		repository.deleteById(id);
	}
	
	@Override
    public Funcionario getByCpf(String cpf) {
        Funcionario funcionario = repository.getFuncionariosByCpf(cpf);
        if (funcionario != null) {
            return funcionario;
        }
        return null;
    }

}
