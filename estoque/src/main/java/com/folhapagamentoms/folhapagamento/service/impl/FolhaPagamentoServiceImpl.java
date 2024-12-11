package com.folhapagamentoms.folhapagamento.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folhapagamentoms.folhapagamento.dto.FolhaPagamentoDTO;
import com.folhapagamentoms.folhapagamento.entity.FolhaPagamento;
import com.folhapagamentoms.folhapagamento.exception.AplicacaoException;
import com.folhapagamentoms.folhapagamento.mapper.FolhaPagamentoMapper;
import com.folhapagamentoms.folhapagamento.repository.FolhaPagamentoRepository;
import com.folhapagamentoms.folhapagamento.service.FolhaPagamentoService;

@Service
public class FolhaPagamentoServiceImpl implements FolhaPagamentoService {
	@Autowired
	private FolhaPagamentoRepository repository;

	@Autowired
	private FolhaPagamentoMapper mapper;

	@Override
	public List<FolhaPagamento> getAll() {
		List<FolhaPagamento> listaDados = repository.findAll();

		if (listaDados == null) {
			return new ArrayList<>();
		}
		return listaDados;
	}

	@Override
	public FolhaPagamento getFolhaPagamento(Long id) {
		Optional<FolhaPagamento> dado = repository.findById(id);

		if (!dado.isPresent()) {
			return new FolhaPagamento();
		}
		return dado.get();
	}

	@Override
	public FolhaPagamento create(FolhaPagamentoDTO dto) {
		FolhaPagamento dado = repository.getFolhaPagamento(dto.getSalario());

		if (dado != null) {
			return new FolhaPagamento();
		}

		dado = mapper.convertDtoToEntity(dto);

		return repository.save(dado);
	}

	@Override
	public FolhaPagamento update(FolhaPagamentoDTO dto) {
		FolhaPagamento dadoAtual = repository.getFolhaPagamento(dto.getSalario());

		if (dadoAtual == null) {
			return new FolhaPagamento();
		}

		FolhaPagamento dadoNovo = mapper.convertDtoToEntity(dto);

		dadoNovo = this.validaCamposUpdate(dadoAtual, dadoNovo);

		return repository.save(dadoNovo);
	}

	public FolhaPagamento validaCamposUpdate(FolhaPagamento dadoAtual, FolhaPagamento dadoNovo) {

		dadoAtual.setSalario((dadoAtual.getSalario().equals(dadoNovo.getSalario()) ? dadoAtual.getSalario()
				: dadoNovo.getSalario()));

		return dadoAtual;
	}

	@Override
	public void delete(Long id) {
		Optional<FolhaPagamento> dado = repository.findById(id);

		if (!dado.isPresent()) {
			throw new AplicacaoException("Produto não existe.");
		}
		repository.deleteById(id);
	}

	@Override
	public List<FolhaPagamento> getFolha(String cpf) {
		List<FolhaPagamento> listFolha = repository.getListaFolha(cpf);

		return listFolha;
	}

}