package edu.ifam.dra.chat.dto;

import java.util.Calendar;

import edu.ifam.dra.chat.model.Mensagem;

public class MensagemDTO {

    private Long id;
    private Calendar dataHora;
    private String conteudo;
    private Long emissor;
    private Long receptor;

    public MensagemDTO() {
    }

    public MensagemDTO(Long id, Calendar dataHora, String conteudo, Long emissor, Long receptor) {
        this.id = id;
        this.dataHora = dataHora;
        this.conteudo = conteudo;
        this.emissor = emissor;
        this.receptor = receptor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDataHora() {
        return dataHora;
    }

    public void setDataHora(Calendar dataHora) {
        this.dataHora = dataHora;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Long getEmissor() {
        return emissor;
    }

    public void setEmissor(Long emissor) {
        this.emissor = emissor;
    }

    public Long getReceptor() {
        return receptor;
    }

    public void setReceptor(Long receptor) {
        this.receptor = receptor;
    }
}