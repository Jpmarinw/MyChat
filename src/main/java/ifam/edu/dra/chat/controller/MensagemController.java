package ifam.edu.dra.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Mensagem> enviarMensagem(@RequestBody Mensagem mensagem) {
        Mensagem mensagemEnviada = mensagemService.enviarMensagem(mensagem);
        return ResponseEntity.ok(mensagemEnviada);
    }

    @GetMapping("/receber/{id}")
    public ResponseEntity<List<Mensagem>> receberMensagens(@RequestParam Long receptorId) {
        // Você pode passar o ID do receptor como parâmetro na URL ou da maneira que preferir.
        Contato receptor = new Contato(); // Você precisa encontrar o Contato com base no ID fornecido.
        List<Mensagem> mensagens = mensagemService.receberMensagens(receptor);
        return ResponseEntity.ok(mensagens);
    }
}
