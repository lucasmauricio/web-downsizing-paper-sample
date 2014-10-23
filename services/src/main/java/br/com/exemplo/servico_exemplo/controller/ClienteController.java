package br.com.exemplo.servico_exemplo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import br.com.exemplo.servico_exemplo.App;
import br.com.exemplo.servico_exemplo.domain.Cliente;
import br.com.exemplo.servico_exemplo.service.ClienteJsonFormatter;
import br.com.exemplo.servico_exemplo.service.ClienteService;
import br.com.exemplo.servico_exemplo.service.ListJsonFormatter;
import br.com.exemplo.servico_exemplo.service.exception.ClienteCadastroInvalidoException;

@Controller
public class ClienteController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/clientes", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody void listarTodosClientes(HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("ISO-8859-1");
		response.setStatus(HttpStatus.OK.value());
		try {
			response.getWriter().print(new ListJsonFormatter(clienteService.listarTodosClientes()));
		} catch (IOException e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody void listarTodosClientes(HttpServletResponse response, @PathVariable String id) {
		response.setContentType("application/json");
		response.setCharacterEncoding("ISO-8859-1");
		try {
			Cliente cliente = clienteService.buscarCliente(Long.parseLong(id));
			response.getWriter().print((new ClienteJsonFormatter(cliente)).toString());
		} catch (NumberFormatException e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			e.printStackTrace();
		} catch (IOException e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/clientes/cadastrar", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody void cadastrarCliente(@RequestBody Cliente novoCliente, HttpServletResponse response, WebRequest request) {
		response.setContentType("application/json");
		response.setCharacterEncoding("ISO-8859-1");
		try {
			Long novoId = clienteService.cadastrar(novoCliente);
			response.setStatus(HttpStatus.CREATED.value());
			response.setHeader("Location", request.getContextPath() + "/cliente/" + novoId);
		} catch (ClienteCadastroInvalidoException e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			try {
				response.getWriter().print(e.getMessage());
			} catch (IOException e1) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				e1.printStackTrace();
			}
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public @ResponseBody void alterarCliente(@RequestBody Cliente cliente, HttpServletResponse response, @PathVariable String id) {
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("ISO-8859-1");
			clienteService.alterarCliente(Long.parseLong(id), cliente);
			response.setStatus(HttpStatus.CREATED.value());
		} catch (NumberFormatException e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			e.printStackTrace();
		} catch (ClienteCadastroInvalidoException e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			try {
				response.getWriter().print(e.getMessage());
			} catch (IOException e1) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/cliente/{id}/excluir", method = RequestMethod.DELETE)
	public @ResponseBody void excluirCliente(HttpServletResponse response, @PathVariable String id) {
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("ISO-8859-1");
			clienteService.excluirCliente(Long.parseLong(id));
			response.setStatus(HttpStatus.OK.value());
		} catch (NumberFormatException e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			e.printStackTrace();
		}
	}

}
