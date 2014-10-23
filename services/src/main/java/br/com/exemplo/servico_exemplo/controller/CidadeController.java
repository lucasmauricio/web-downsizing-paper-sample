package br.com.exemplo.servico_exemplo.controller;


import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.exemplo.servico_exemplo.domain.Cidade;
import br.com.exemplo.servico_exemplo.service.JsonFormatter;
import br.com.exemplo.servico_exemplo.service.UnidadeFederativaService;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;


@Controller
public class CidadeController {

	private static final Logger logger = LoggerFactory
			.getLogger(CidadeController.class);
	
	@Autowired
	private UnidadeFederativaService ufService;// = new UnidadeFederativaService();

    
    
    
    
    
	@RequestMapping(value = "/cidade", method = RequestMethod.GET, produces="application/json")
//	public ResponseEntity<List<Produto>> listarProdutos() { //(ModelMap model)
	public @ResponseBody void listarProdutosMock(HttpServletResponse response) { //(ModelMap model)
//		logger.info("em listarProdutos");
//		ProdutoService produtoService = new ProdutoService();
//		List<Produto> produtos = produtoService.getProdutos(); 
//		logger.info("produtos="+produtos);
//		ResponseEntity<List<Produto>> resposta = new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
//		logger.info(" response \n");
//		logger.info(resposta.toString());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);
		try {
			response.getWriter().print(" [{\"id\":7788,\"descricao\":\"G 1115\",\"marca\":\"Aveli G\",\"modelo\":\"G1115\",\"fabricante_id\":\"8800\","
					+ "\"categoria_id\":\"9900\",\"observacao\":\"\"},{\"id\":7799,\"descricao\":\"Lenovo i7 ultra\",\"marca\":\"Lenovo i7\",\"modelo\":"
					+ "\"algum i7\",\"fabricante_id\":\"8888\",\"categoria_id\":\"9900\",\"observacao\":\"\"},{\"id\":7791,\"descricao\":\"hd rápico\","
					+ "\"marca\":\"barracuda\",\"modelo\":\"xx897987\",\"fabricante_id\":\"8877\",\"categoria_id\":\"9999\",\"observacao\":\"esse dava "
					+ "erro.\"},{\"id\":7797,\"descricao\":\"avel Titanium\",\"marca\":\"Titanium\",\"modelo\":\"B 154 SE2 Plus\",\"fabricante_id\":"
					+ "\"8800\",\"categoria_id\":\"9900\",\"observacao\":\"\"},{\"id\":7796,\"descricao\":\"Avel B153\",\"marca\":\"Titanium\",\"modelo"
					+ "\":\"B153\",\"fabricante_id\":\"8800\",\"categoria_id\":\"9900\",\"observacao\":\"\"}] ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@RequestMapping(value = "/{uf}/cidades", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody void listarCidadesDaUf(HttpServletResponse response, @PathVariable String uf) {
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		if (!ufService.validarUf(uf.toUpperCase())) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		} else {
			List<Cidade> cidadesUf = ufService.getCidades(uf);
			response.setStatus(HttpStatus.OK.value());
			try {
				response.getWriter().print((new JsonFormatter(cidadesUf)).toString());
			} catch (IOException e) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				e.printStackTrace();
			}
		}
	}
	
	/**
	
	
	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Produto> exibirProduto(ModelMap model, @PathVariable String id) {
		logger.info("em exibirProduto");
		ProdutoService produtoService = new ProdutoService();
		Produto produto = produtoService.getProdutoPorId(id);
//		System.out.println("  o produto achado com o id="+id+" é: " + produto);
//		model.addAttribute("produto", produto);
//		return "exibirProduto";
		logger.info("produto="+produto);
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}

	@RequestMapping(value = "/produto/{id}/atributos", method = RequestMethod.GET)
	public String exibirAtributosProduto(ModelMap model, @PathVariable String id) {
		ProdutoService produtoService = new ProdutoService();
		Produto produto = produtoService.getProdutoPorId(id);
		model.addAttribute("produto", produto);
		return "exibirAtributosProduto";
	}

	@RequestMapping(value = "/produto/{id}/atributos/novo", method = RequestMethod.GET)
	public String exibirCadastroAtributoProduto(ModelMap model, @PathVariable String id) {
		ProdutoService produtoService = new ProdutoService();
		Produto produto = produtoService.getProdutoPorId(id);
		AtributoProduto novoAtributo = new AtributoProduto();
//		model.addAttribute("tiposAtributo", tipoAtributoService.getTipoAtributos()); 
		model.addAttribute("acao", "/produto/" + produto.getId() + "/atributos/novo");
		model.addAttribute("novoAtributo", novoAtributo);
		model.addAttribute("produto", produto);
		return "exibirAtributosProduto";
	}

	@RequestMapping(value = "/produto/{id}/atributos/novo", method = RequestMethod.POST)
	public String novoAtributoProduto(@ModelAttribute("novoAtributo") AtributoProduto novoAtributo, 
			@PathVariable String id, HttpServletRequest request) { // , BindingResult result, SessionStatus status
		logger.info("em novoAtributoProduto - usando POST");
		
		
		
//		
//		duas possibilidades
//		1) usar interceptor (parece que ficaria tudo na mão)
//		2) ver uma forma de tornar esse log automático.
//		
		
		
		logger.info("Request: " + request.getRequestURL());
		ProdutoService produtoService = new ProdutoService();
		Produto produto = produtoService.getProdutoPorId(id);
		System.out.println();
		System.out.println(" + inserindo o atributo " + novoAtributo + " no produto id=" + produto.getId());
//		new AtributoProdutoValidator().validate(pet, result);
//        if (result.hasErrors()) {
//            return "pets/createOrUpdatePetForm";
//        } else {
//            produtoService.incluiAtributo(AtributoProduto);
//            status.setComplete();
//            return "redirect:/owners/{ownerId}";
//        }
        produtoService.adicionarAtributo(novoAtributo);
        return "redirect:/produto/{id}";
//		model.addAttribute("produto", produto);
//		return "exibirAtributosProduto";
	}

**/

}
