package com.funcionarioms.funcionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.funcionarioms.funcionario.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>, JpaSpecificationExecutor<Funcionario>{

	@Query(value = "SELECT * FROM FUNCIONARIOS WHERE NOME=:nome", nativeQuery = true)
	Funcionario getFuncionarios(@Param("nome") String nome);
	
	@Query(value = "SELECT * FROM FUNCIONARIOS WHERE UPPER(NOME)=UPPER(:nome) AND UPPER(SOBRENOME)=UPPER(:sobreNome)", nativeQuery = true)
    Funcionario getFuncionariosByNomeESobrenome(@Param("nome") String nome, @Param("sobreNome") String sobreNome);

	@Query(value = "SELECT * FROM FUNCIONARIOS WHERE CPF=:cpf", nativeQuery = true)
	Funcionario getFuncionariosByCpf(@Param("cpf") String cpf);
	
}
