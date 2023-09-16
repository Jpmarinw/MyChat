package ifam.edu.dra.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifam.edu.dra.chat.model.Contato;
import ifam.edu.dra.chat.model.Mensagem;
import ifam.edu.dra.chat.repository.MensagemRepository;

@Service
public class MensagemService {

    @Autowired
    MensagemRepository mensagemRepository;

    public Mensagem enviarMensagem(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    public List<Mensagem> receberMensagens(Contato receptor) {
        return mensagemRepository.findAllByReceptor(receptor);
    }
}

