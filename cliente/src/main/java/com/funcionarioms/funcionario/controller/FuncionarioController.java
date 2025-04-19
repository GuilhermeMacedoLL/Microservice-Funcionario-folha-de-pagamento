package com.funcionarioms.funcionario.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funcionarioms.funcionario.dto.FolhaPagamentoDTO;
import com.funcionarioms.funcionario.dto.FuncionarioDTO;
import com.funcionarioms.funcionario.mapper.FuncionarioMapper;
import com.funcionarioms.funcionario.service.FuncionarioService;
import com.funcionarioms.funcionario.util.MediaType;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private FuncionarioService service;

	@Autowired
	private FuncionarioMapper mapper;

	@GetMapping(value = "/consulta", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<FuncionarioDTO>> getAll() {
		List<FuncionarioDTO> listaDto = new ArrayList<>();
		listaDto = this.mapper.convertListEntityToListDto(this.service.getAll());
		return new ResponseEntity<List<FuncionarioDTO>>(listaDto, HttpStatus.OK);
	}

	@GetMapping(value = "/consulta/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<FuncionarioDTO> getNome(@PathVariable Long id) {
		FuncionarioDTO dto = new FuncionarioDTO();
		dto = this.mapper.convertEntityToDto(this.service.getFuncionarios(id));
		return new ResponseEntity<FuncionarioDTO>(dto, HttpStatus.OK);
	}

	@GetMapping(value = "/salarioByCpf/{cpf}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<FuncionarioDTO> getHistoricoPedido(@PathVariable String cpf) {

		FuncionarioDTO dto = mapper.convertEntityToDto(service.getByCpf(cpf));

		Double salario = 0.00;

		if (cpf != null) {
			String url = "http://localhost:8081/folha/consulta/salarioFuncionario/" + cpf;

			try {

				ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
				if (response.getBody() != null) {
					ObjectMapper objectMapper = new ObjectMapper();
					FolhaPagamentoDTO[] listPagamento = objectMapper.readValue(response.getBody(),
							FolhaPagamentoDTO[].class);
					for (FolhaPagamentoDTO folha : listPagamento) {
						salario = salario + Double.parseDouble(folha.getSalario().replace(".", "").replace(",", "."));
					}
					dto.setSalario(salario.toString());
					return new ResponseEntity<FuncionarioDTO>(dto, HttpStatus.OK);
				}
				return null;
			} catch (Exception e) {
				log.error("Erro ao chamar o serviço de folha de pagamento: ", e);
				return null;
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/pedidos/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> getPedidosByClienteId(@PathVariable Long id) {
		String url = "http://localhost:8082/pedidos/consulta/clienteId/" + id;

		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
			return response;
		} catch (Exception e) {
			log.error("Erro ao chamar o serviço de pedidos: ", e);
			return new ResponseEntity<>("Erro ao chamar o serviço de pedidos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/salvar", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<FuncionarioDTO> create(@RequestBody FuncionarioDTO dtoInput) {
		FuncionarioDTO dto = new FuncionarioDTO();
		dto = this.mapper.convertEntityToDto(this.service.create(dtoInput));
		return new ResponseEntity<FuncionarioDTO>(dto, HttpStatus.CREATED);
	}

	@PutMapping(value = "/alterar", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<FuncionarioDTO> update(@RequestBody FuncionarioDTO dtoInput) {
		FuncionarioDTO dto = new FuncionarioDTO();
		dto = this.mapper.convertEntityToDto(this.service.update(dtoInput));
		return new ResponseEntity<FuncionarioDTO>(dto, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/excluir/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			service.delete(id);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Erro ao excluir funcionario com id: " + id, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao excluir funcionario: " + e.getMessage());
		}
	}
}
