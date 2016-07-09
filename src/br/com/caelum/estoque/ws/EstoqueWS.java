package br.com.caelum.estoque.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless
@WebService(targetNamespace = "http://caelum.com.br/estoque/v1")
public class EstoqueWS {
	private Map<String, ItemEstoque> repositorio = new HashMap<>();

	public EstoqueWS(){
		// populando dados, mapeando codigo para quantidade
		repositorio.put("SOA", new ItemEstoque("SOA",4));
		repositorio.put("TDD", new ItemEstoque("TDD",1));
		repositorio.put("RES", new ItemEstoque("RES",2));
		repositorio.put("LOG", new ItemEstoque("LOG",4));
		repositorio.put("WEB", new ItemEstoque("WEB",1));
		repositorio.put("ARQ", new ItemEstoque("ARQ",2));
	}
	
	@WebMethod(operationName="ItensPeloCodigo")
	@WebResult(name="ItensEstoque")
	
	// header = true forca parametro pelo header
	// ao inves de usuario e senha, poderiamos gerar um token para validar usuario
	public List<ItemEstoque> getQuantidade(@WebParam(name="codigo")List<String> codigos,
										   @WebParam(name = "tokenUsuario",header=true) String token) throws AutorizacaoException{
		if (token == null || !token.equalsIgnoreCase("TOKEN123")) 
			throw new AutorizacaoException("Nao autorizado"); 
		
		ArrayList<ItemEstoque> itens = new ArrayList<>();
		
		for (String codigo:codigos){
			if (repositorio.containsKey(codigo)) 
				itens.add(repositorio.get(codigo));
		}
		
		return itens;
	}
	
}
