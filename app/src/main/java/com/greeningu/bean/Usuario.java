package com.greeningu.bean;

import java.io.Serializable;

/**
 * Created by Jadson on 03/05/2015.
 */
import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {

    private static final long serialVersionUID = -5918468404482711671L;

    private Integer idUsuario;

    private List<Voto> votos;

    private List<Comentario> comentarios;

    private String nome;

    private String sobrenome;

    private String email;

    private String login;

    private String senha;

    private char sexo;

    private Integer pontuacao;

    private Permissao permissao;

    private List<Postagem> postagens;

    private List<Comunidade> comunidades;


    public Usuario() {}

    public Usuario(Integer idUsuario, String nome, String sobrenome,
                   String email, String login, String senha, char sexo,
                   Integer pontuacao, Permissao permissao, List<Postagem> postagens) {
        super();
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.sexo = sexo;
        this.pontuacao = pontuacao;
        this.permissao = permissao;
        this.postagens = postagens;
    }

    public Usuario(Integer idUsuario, List<Voto> votos,
                   List<Comentario> comentarios, String nome, String sobrenome,
                   String email, String login, String senha, char sexo,
                   Integer pontuacao, Permissao permissao, List<Postagem> postagens,
                   List<Comunidade> comunidades) {
        super();
        this.idUsuario = idUsuario;
        this.votos = votos;
        this.comentarios = comentarios;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.sexo = sexo;
        this.pontuacao = pontuacao;
        this.permissao = permissao;
        this.postagens = postagens;
        this.comunidades = comunidades;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public List<Postagem> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<Postagem> postagens) {
        this.postagens = postagens;
    }

    public List<Comunidade> getComunidades() {
        return comunidades;
    }

    public void setComunidades(List<Comunidade> comunidades) {
        this.comunidades = comunidades;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((comentarios == null) ? 0 : comentarios.hashCode());
        result = prime * result
                + ((comunidades == null) ? 0 : comunidades.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result
                + ((idUsuario == null) ? 0 : idUsuario.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result
                + ((permissao == null) ? 0 : permissao.hashCode());
        result = prime * result
                + ((pontuacao == null) ? 0 : pontuacao.hashCode());
        result = prime * result
                + ((postagens == null) ? 0 : postagens.hashCode());
        result = prime * result + ((senha == null) ? 0 : senha.hashCode());
        result = prime * result + sexo;
        result = prime * result
                + ((sobrenome == null) ? 0 : sobrenome.hashCode());
        result = prime * result + ((votos == null) ? 0 : votos.hashCode());
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
        Usuario other = (Usuario) obj;
        if (comentarios == null) {
            if (other.comentarios != null)
                return false;
        } else if (!comentarios.equals(other.comentarios))
            return false;
        if (comunidades == null) {
            if (other.comunidades != null)
                return false;
        } else if (!comunidades.equals(other.comunidades))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (idUsuario == null) {
            if (other.idUsuario != null)
                return false;
        } else if (!idUsuario.equals(other.idUsuario))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (permissao == null) {
            if (other.permissao != null)
                return false;
        } else if (!permissao.equals(other.permissao))
            return false;
        if (pontuacao == null) {
            if (other.pontuacao != null)
                return false;
        } else if (!pontuacao.equals(other.pontuacao))
            return false;
        if (postagens == null) {
            if (other.postagens != null)
                return false;
        } else if (!postagens.equals(other.postagens))
            return false;
        if (senha == null) {
            if (other.senha != null)
                return false;
        } else if (!senha.equals(other.senha))
            return false;
        if (sexo != other.sexo)
            return false;
        if (sobrenome == null) {
            if (other.sobrenome != null)
                return false;
        } else if (!sobrenome.equals(other.sobrenome))
            return false;
        if (votos == null) {
            if (other.votos != null)
                return false;
        } else if (!votos.equals(other.votos))
            return false;
        return true;
    }


}
