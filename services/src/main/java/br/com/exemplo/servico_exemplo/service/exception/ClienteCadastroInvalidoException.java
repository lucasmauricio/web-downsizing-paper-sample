package br.com.exemplo.servico_exemplo.service.exception;

public class ClienteCadastroInvalidoException extends Exception {

	private static final long serialVersionUID = 3219278959448974377L;

	public ClienteCadastroInvalidoException(String mensagem) {
		super(mensagem);
	}

}
