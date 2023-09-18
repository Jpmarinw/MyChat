package ifam.edu.dra.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ifam.edu.dra.chat.dto.MensagemDTO;
import ifam.edu.dra.chat.model.Contato;
import ifam.edu.dra.chat.model.Mensagem;
import ifam.edu.dra.chat.service.MensagemService;

import java.util.List;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    MensagemService mensagemService;

    @PostMapping("/enviar")
    public ResponseEntity<MensagemDTO> enviarMensagem(@RequestBody MensagemDTO mensagemDTO) {
        // Crie um objeto Mensagem a partir do DTO (você precisa converter os IDs em Contatos reais)
        Mensagem mensagem = new Mensagem();
        mensagem.setDataHora(mensagemDTO.getDataHora());
        mensagem.setConteudo(mensagemDTO.getConteudo());

        Contato emissor = new Contato();
        emissor.setId(mensagemDTO.getEmissor());
        mensagem.setEmissor(emissor);

        Contato receptor = new Contato();
        receptor.setId(mensagemDTO.getReceptor());
        mensagem.setReceptor(receptor);

        // Envie a mensagem e converta a resposta para DTO
        Mensagem mensagemEnviada = mensagemService.enviarMensagem(mensagem);

        // Converta a resposta para DTO
        MensagemDTO mensagemEnviadaDTO = new MensagemDTO(
                mensagemEnviada.getId(),
                mensagemEnviada.getDataHora(),
                mensagemEnviada.getConteudo(),
                mensagemEnviada.getEmissor().getId(),
                mensagemEnviada.getReceptor().getId()
        );

        return ResponseEntity.ok(mensagemEnviadaDTO);
    }

    // Métodos para receber mensagens (você pode implementar de acordo com suas necessidades)
}
