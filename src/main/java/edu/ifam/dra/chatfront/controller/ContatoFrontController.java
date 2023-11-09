package edu.ifam.dra.chatfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.ifam.dra.chatfront.model.Contato;
import edu.ifam.dra.chatfront.service.ContatoFrontService;

@Controller
public class ContatoFrontController {
	
	@Autowired
	ContatoFrontService contatoFrontService;

	@GetMapping("/mostrarContatos")
	public String mostraContatos(Model model) {
		
		model.addAttribute("contatos", contatoFrontService.getContatos());
		return "listaContatos";
		
	}
	
	@GetMapping("/novoContato")
	public String novoContato(Contato contato) {
		return "novoContato";
	}
	
    @PostMapping("/inserircontato")
    public String addUser(@Validated Contato contato, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "novoContato";
        }
        
        contatoFrontService.postContato(contato);
        return "redirect:/mostrarContatos";
    }
}
