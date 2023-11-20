package edu.ifam.dra.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifam.dra.chat.dto.MensagemDTO;
import edu.ifam.dra.chat.model.Contato;
import edu.ifam.dra.chat.model.Mensagem;
import edu.ifam.dra.chat.repositories.MensagemRepository;

@Service
public class MensagemService {

    @Autowired
    MensagemRepository mensagemRepository;
    
    public List<Mensagem> getMensagens(){
		return mensagemRepository.findAll();
	}

    public Mensagem enviarMensagem(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    public List<Mensagem> receberMensagens(Contato receptor) {
        return mensagemRepository.findAllByReceptor(receptor);
    }
}