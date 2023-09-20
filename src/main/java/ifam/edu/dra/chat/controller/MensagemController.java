package ifam.edu.dra.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ifam.edu.dra.chat.dto.MensagemDTO;
import ifam.edu.dra.chat.model.Contato;
import ifam.edu.dra.chat.model.Mensagem;
import ifam.edu.dra.chat.service.MensagemService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    MensagemService mensagemService;

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


