	package com.IniciandoComSpringMVC.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.IniciandoComSpringMVC.cobranca.model.StatusTitulo;
import com.IniciandoComSpringMVC.cobranca.model.Titulo;
import com.IniciandoComSpringMVC.cobranca.repository.Titulos;
import com.IniciandoComSpringMVC.cobranca.service.CadastroTituloService;

@Controller
@RequestMapping("/titulos")
public class TituloControler {
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@Autowired
	private Titulos titulos;
	
	@Autowired
	private CadastroTituloService cadastroTituloService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		//mv.addObject("todoStatusTitulo", StatusTitulo.values());
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes){
		if(errors.hasErrors()){
			return CADASTRO_VIEW;
		}
		
		try{//TODO : Salvar no banco de dados.
			cadastroTituloService.salvar(titulo);
			attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
			return "redirect:/titulos/novo";
		}catch(IllegalArgumentException e){
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping
	public ModelAndView pesquisar(){
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
	}
	
	@RequestMapping(value="{codigo}" , method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo , RedirectAttributes attributes){
		cadastroTituloService.excluir(codigo);
		attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return "redirect:/titulos";
	}
	
	@ModelAttribute("todoStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}
}
