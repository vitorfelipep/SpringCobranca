package com.IniciandoComSpringMVC.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.IniciandoComSpringMVC.cobranca.model.Titulo;
import com.IniciandoComSpringMVC.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloControler {
	
	@Autowired
	private Titulos titulos;
	
	@RequestMapping("novo")
	public String novo(){
		return "CadastroTitulo";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Titulo titulo){
		//TODO : Salvar no banco de dados.
		System.out.println(">>> " + titulo.getDescricao());
		System.out.println(">>>" + titulo.getDataVencimento());
		titulos.save(titulo);
		
		System.out.println("Dados salvo com sucesso!");
		return "CadastroTitulo";
	}
}
