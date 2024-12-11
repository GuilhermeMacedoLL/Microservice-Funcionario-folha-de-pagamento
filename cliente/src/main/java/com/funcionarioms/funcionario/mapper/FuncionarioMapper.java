package com.funcionarioms.funcionario.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.funcionarioms.funcionario.dto.FuncionarioDTO;
import com.funcionarioms.funcionario.entity.Funcionario;

@Component
public class FuncionarioMapper {

	public FuncionarioDTO convertEntityToDto(Funcionario dado) {
		FuncionarioDTO dto = new FuncionarioDTO();

		if (dado != null) {
			dto.setId(dado.getId());
			dto.setNome(dado.getNome());
			dto.setSobreNome(dado.getSobreNome());
			dto.setCpf(dado.getCpf());
		}
		return dto;
	}

	public Funcionario convertDtoToEntity(FuncionarioDTO dto) {
		Funcionario entity = new Funcionario();

		if (dto != null) {
			entity.setId(dto.getId());
			entity.setNome(dto.getNome());
			entity.setSobreNome(dto.getSobreNome());
			entity.setCpf(dto.getCpf());
		}
		return entity;
	}

	public List<FuncionarioDTO> convertListEntityToListDto(List<Funcionario> listaDado) {
		List<FuncionarioDTO> listDto = new ArrayList<>();

		if (listDto != null) {
			for (Funcionario dado : listaDado) {
				listDto.add(convertEntityToDto(dado));
			}
		}
		return listDto;

	}

	public List<Funcionario> convertListDtoToListEntity(List<FuncionarioDTO> listaDto) {
		List<Funcionario> listDado = new ArrayList<>();

		if (listaDto != null) {
			for (FuncionarioDTO dado : listaDto) {
				listDado.add(convertDtoToEntity(dado));
			}
		}

		return listDado;
	}

}
