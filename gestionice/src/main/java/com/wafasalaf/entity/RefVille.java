package com.wafasalaf.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REF_VILLE")
public class RefVille {
	@Id
	private Long id;
	private String libelle;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public RefVille(Long id) {
		super();
		this.id = id;
	}
	public RefVille() {
		super();
	}


	

}
