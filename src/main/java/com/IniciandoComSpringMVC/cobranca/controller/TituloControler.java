package com.IniciandoComSpringMVC.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.IniciandoComSpringMVC.cobranca.model.StatusTitulo;
import com.IniciandoComSpringMVC.cobranca.model.Titulo;
import com.IniciandoComSpringMVC.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloControler {
	
	@Autowired
	private Titulos titulos;
	
	@RequestMapping("novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("todoStatusTitulo", StatusTitulo.values());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo){
		//TODO : Salvar no banco de dados.
		System.out.println(">>> " + titulo.getDescricao());
		System.out.println(">>>" + titulo.getDataVencimento());
		titulos.save(titulo);
		
		System.out.println("Dados salvo com sucesso!");
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem", "Título salvo com sucesso!");
		return mv;
	}
	
	@RequestMapping
	public String pesquisar(){
		return "PesquisaTitulos";
	}
	
	@ModelAttribute("todoStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}
}
