package com.greeningu.bean;

import java.io.Serializable;
import java.util.Date;

public class Voto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3594842836101944206L;
	private Integer id;
	private Date data;
	private Integer idPostagem;
	private Integer idUsuario;
	private Integer pontos;	
	
	public Voto(){}

	public Voto(Integer id, Date data, Integer idPostagem, Integer idUsuario,
			Integer pontos) {
		super();
		this.id = id;
		this.data = data;
		this.idPostagem = idPostagem;
		this.idUsuario = idUsuario;
		this.pontos = pontos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(Integer idPostagem) {
		this.idPostagem = idPostagem;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idPostagem == null) ? 0 : idPostagem.hashCode());
		result = prime * result
				+ ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result + ((pontos == null) ? 0 : pontos.hashCode());
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
		Voto other = (Voto) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idPostagem == null) {
			if (other.idPostagem != null)
				return false;
		} else if (!idPostagem.equals(other.idPostagem))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (pontos == null) {
			if (other.pontos != null)
				return false;
		} else if (!pontos.equals(other.pontos))
			return false;
		return true;
	}
}
