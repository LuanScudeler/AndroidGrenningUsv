package com.greeningu.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jadson on 03/05/2015.
 */
public class Voto implements Serializable {

    private static final long serialVersionUID = -7758872134617860817L;

    private Integer idVoto;

    private Postagem postagem;

    private Usuario usuario;

    private Date data;

    private Integer pontos;

    public Integer getIdVoto() {
        return idVoto;
    }

    public void setIdVoto(Integer idVoto) {
        this.idVoto = idVoto;
    }

    public Postagem getPostagem() {
        return postagem;
    }

    public void setPostagem(Postagem postagem) {
        this.postagem = postagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}

