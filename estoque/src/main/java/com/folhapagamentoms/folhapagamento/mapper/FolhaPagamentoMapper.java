package com.folhapagamentoms.folhapagamento.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.folhapagamentoms.folhapagamento.dto.FolhaPagamentoDTO;
import com.folhapagamentoms.folhapagamento.entity.FolhaPagamento;

@Component
public class FolhaPagamentoMapper {

	public FolhaPagamentoDTO convertEntityToDto(FolhaPagamento dado) {
		FolhaPagamentoDTO dto = new FolhaPagamentoDTO();
		
		if (dado != null) {
			dto.setId(dado.getId());
			dto.setSalario(dado.getSalario());
			dto.setCpf(dado.getCpf());
		}
		
		return dto;
	}
	
	public FolhaPagamento convertDtoToEntity(FolhaPagamentoDTO dto) {
		FolhaPagamento entity = new FolhaPagamento();
		
		if (dto != null) {
			entity.setId(dto.getId());
			entity.setSalario(dto.getSalario());
			entity.setCpf(dto.getCpf());
		}
		
		return entity;
	}
	
	public List<FolhaPagamentoDTO> convertListEntityToListDto(List<FolhaPagamento> listaDado){
		List<FolhaPagamentoDTO> listDto = new ArrayList<>();
		
		if (listDto != null) {
			for (FolhaPagamento dado : listaDado) {
				listDto.add(convertEntityToDto(dado));
			}
		}
		
		return listDto;
	}
	
	public List<FolhaPagamento> convertListDtoToListEntity(List<FolhaPagamentoDTO> listaDto) {
		List<FolhaPagamento> listDado = new ArrayList<>();

		if (listaDto != null) {
			for (FolhaPagamentoDTO dado : listaDto) {
				listDado.add(convertDtoToEntity(dado));
			}
		}

		return listDado;
	}
}
