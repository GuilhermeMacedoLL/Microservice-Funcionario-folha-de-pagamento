package com.folhapagamentoms.folhapagamento.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.folhapagamentoms.folhapagamento.dto.FolhaPagamentoDTO;
import com.folhapagamentoms.folhapagamento.mapper.FolhaPagamentoMapper;
import com.folhapagamentoms.folhapagamento.service.FolhaPagamentoService;
import com.folhapagamentoms.folhapagamento.util.MediaType;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/folha")
public class FolhaPagamentoController {

	@Autowired
	private FolhaPagamentoService service;

	@Autowired
	private FolhaPagamentoMapper mapper;

	@GetMapping(value = "/consulta", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<FolhaPagamentoDTO>> getAll() {
		List<FolhaPagamentoDTO> listaDto = new ArrayList<>();
		listaDto = this.mapper.convertListEntityToListDto(this.service.getAll());
		return new ResponseEntity<List<FolhaPagamentoDTO>>(listaDto, HttpStatus.OK);
	}

	@GetMapping(value = "/consulta/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<FolhaPagamentoDTO> getNomeProduto(@PathVariable Long id) {
		FolhaPagamentoDTO dto = new FolhaPagamentoDTO();
		dto = this.mapper.convertEntityToDto(this.service.getFolhaPagamento(id));
		return new ResponseEntity<FolhaPagamentoDTO>(dto, HttpStatus.OK);
	}

	@GetMapping(value = "/consulta/salarioFuncionario/{cpf}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<FolhaPagamentoDTO>> getFuncionarioCpf(@PathVariable String cpf) {
		List<FolhaPagamentoDTO> listaDto = new ArrayList<>();
		listaDto = this.mapper.convertListEntityToListDto(this.service.getFolha(cpf));
		return new ResponseEntity<List<FolhaPagamentoDTO>>(listaDto, HttpStatus.OK);
	}

	@PostMapping(value = "/salvar", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<FolhaPagamentoDTO> create(@RequestBody FolhaPagamentoDTO dtoInput) {
		FolhaPagamentoDTO dto = new FolhaPagamentoDTO();
		dto = this.mapper.convertEntityToDto(this.service.create(dtoInput));
		return new ResponseEntity<FolhaPagamentoDTO>(dto, HttpStatus.CREATED);
	}

	@PutMapping(value = "/alterar", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<FolhaPagamentoDTO> update(@RequestBody FolhaPagamentoDTO dtoInput) {
		FolhaPagamentoDTO dto = new FolhaPagamentoDTO();
		dto = this.mapper.convertEntityToDto(this.service.update(dtoInput));
		return new ResponseEntity<FolhaPagamentoDTO>(dto, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/excluir/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			service.delete(id);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Erro ao excluir folha de pagamento com id: " + id, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao excluir folha de pagamento: " + e.getMessage());
		}
	}
}