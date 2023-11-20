package edu.ifam.dra.chat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifam.dra.chat.dto.MensagemDTO;
import edu.ifam.dra.chat.model.Contato;
import edu.ifam.dra.chat.model.Mensagem;
import edu.ifam.dra.chat.service.MensagemService;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    MensagemService mensagemService;
    
    @GetMapping
	ResponseEntity<List<Mensagem>> getMensagens(){
		List<Mensagem> mensagens = mensagemService.getMensagens();
		if(mensagens.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagens);
		return ResponseEntity.ok(mensagens);
		
	}

    @PostMapping("/enviar")
    public ResponseEntity<MensagemDTO> enviarMensagem(@RequestBody MensagemDTO mensagemDTO) {
        Mensagem mensagem = new Mensagem();
        mensagem.setDataHora(mensagemDTO.getDataHora());
        mensagem.setConteudo(mensagemDTO.getConteudo());

        Contato emissor = new Contato();
        emissor.setId(mensagemDTO.getEmissor());
        mensagem.setEmissor(emissor);

        Contato receptor = new Contato();
        receptor.setId(mensagemDTO.getReceptor());
        mensagem.setReceptor(receptor);

        Mensagem mensagemEnviada = mensagemService.enviarMensagem(mensagem);

        MensagemDTO mensagemEnviadaDTO = new MensagemDTO(
                mensagemEnviada.getId(),
                mensagemEnviada.getDataHora(),
                mensagemEnviada.getConteudo(),
                mensagemEnviada.getEmissor().getId(),
                mensagemEnviada.getReceptor().getId()
        );

        return ResponseEntity.ok(mensagemEnviadaDTO);
    }
    
    @GetMapping("/receber/{id}")
    public ResponseEntity<List<MensagemDTO>> receberMensagensEnviadas(@PathVariable Contato id) {
       
        List<Mensagem> mensagensEnviadas = mensagemService.receberMensagens(id);

        List<MensagemDTO> mensagemDTOs = new ArrayList<>();
        for (Mensagem mensagem : mensagensEnviadas) {
            MensagemDTO mensagemDTO = new MensagemDTO(
                mensagem.getId(),
                mensagem.getDataHora(),
                mensagem.getConteudo(),
                mensagem.getEmissor().getId(),
                mensagem.getReceptor().getId()
            );
            mensagemDTOs.add(mensagemDTO);
        }
        return ResponseEntity.ok(mensagemDTOs);
    }
}