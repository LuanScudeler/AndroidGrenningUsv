package com.greeningu.bean;

import java.io.Serializable;

/**
 * Created by Jadson on 03/05/2015.
 */
public class Comunidade implements Serializable {

    private static final long serialVersionUID = -6490059997087316904L;

    private Integer idComunidade;

    private String nome;

    private int usarioLider;

    public Comunidade(){}

    public Integer getIdComunidade() {
        return idComunidade;
    }

    public void setIdComunidade(Integer idComunidade) {
        this.idComunidade = idComunidade;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Comunidade(Integer id, String nome) {
        super();
        this.idComunidade = id;
        this.nome = nome;
    }

    public Integer getId() {
        return idComunidade;
    }

    public void setId(Integer id) {
        this.idComunidade = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idComunidade == null) ? 0 : idComunidade.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comunidade other = (Comunidade) obj;
        if (idComunidade == null) {
            if (other.idComunidade != null)
                return false;
        } else if (!idComunidade.equals(other.idComunidade))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }


}

