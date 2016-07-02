package br.com.caelum.estoque.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Stateless
@WebService
public class EstoqueWS {
	private Map<String, ItemEstoque> repositorio = new HashMap<>();

	public EstoqueWS(){
		// populando dados, mapeando codigo para quantidade
		repositorio.put("SOA", new ItemEstoque("SOA",5));
		repositorio.put("TDD", new ItemEstoque("TDD",1));
		repositorio.put("RES", new ItemEstoque("RES",2));
		repositorio.put("LOG", new ItemEstoque("LOG",4));
		repositorio.put("WEB", new ItemEstoque("WEB",1));
		repositorio.put("ARQ", new ItemEstoque("ARQ",2));
	}
	
	@WebMethod
	public List<ItemEstoque> getQuantidade(List<String> codigos){
		ArrayList<ItemEstoque> itens = new ArrayList<>();
		
		if (codigos == null || codigos.isEmpty()) 
			return itens;
		
		for (String codigo:codigos){
			if (repositorio.containsKey(codigo)) 
				itens.add(repositorio.get(codigo));
		}
		
		return itens;
	}
	
}