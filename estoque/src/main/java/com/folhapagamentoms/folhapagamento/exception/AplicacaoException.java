package com.folhapagamentoms.folhapagamento.exception;

public class AplicacaoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	
	public AplicacaoException() {}

	
	public AplicacaoException(String message)
    {
       super(message);
    }

}