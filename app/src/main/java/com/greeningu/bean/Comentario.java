package com.greeningu.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jadson on 03/05/2015.
 */
public class Comentario implements Serializable {

    private static final long serialVersionUID = -3030353956080536334L;

    private Integer idComentario;

    private Date data;

    private String comentario;

    private Usuario usuario;

    private Postagem postagem;

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Postagem getPostagem() {
        return postagem;
    }

    public void setPostagem(Postagem postagem) {
        this.postagem = postagem;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }



}

