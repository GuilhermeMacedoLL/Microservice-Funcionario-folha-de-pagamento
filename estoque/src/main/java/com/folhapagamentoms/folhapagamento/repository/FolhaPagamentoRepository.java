package com.folhapagamentoms.folhapagamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.folhapagamentoms.folhapagamento.entity.FolhaPagamento;

public interface FolhaPagamentoRepository extends JpaRepository<FolhaPagamento, Long>, JpaSpecificationExecutor<FolhaPagamento> {

	@Query(value = "SELECT * FROM FOLHA WHERE CPF=:cpf", nativeQuery = true)
	List<FolhaPagamento> getListaFolha(@Param("cpf") String cpf);
	
	@Query(value = "SELECT * FROM FOLHA WHERE SALARIO=:salario", nativeQuery = true)
	FolhaPagamento getFolhaPagamento(@Param("salario") String salario);

}
