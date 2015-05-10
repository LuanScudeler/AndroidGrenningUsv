package com.greeningu.bean;

import java.io.Serializable;

public class Comunidade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6849170179649062128L;
	private Integer id;
	private String nome;
	private int usarioLider;

	public Comunidade(){}

	public Comunidade(Integer id, String nome, int usarioLider) {
		super();
		this.id = id;
		this.nome = nome;
		this.usarioLider = usarioLider;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getUsarioLider() {
		return usarioLider;
	}

	public void setUsarioLider(int usarioLider) {
		this.usarioLider = usarioLider;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + usarioLider;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (usarioLider != other.usarioLider)
			return false;
		return true;
	}
}
