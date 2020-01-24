package com.wafasalaf.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REF_ENTREPRISE")
public class EntrepriseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String ice;
	private Long rc;
	private String raisonSocial;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vile_id")
	private RefVille ville;
	private String adresse;
	private String etat;
	private String dateCreation;

	public String getIce() {
		return ice;
	}

	public void setIce(String ice) {
		this.ice = ice;
	}

	public Long getRc() {
		return rc;
	}

	public void setRc(Long rc) {
		this.rc = rc;
	}

	public String getRaisonSocial() {
		return raisonSocial;
	}

	public void setRaisonSocial(String raisonSocial) {
		this.raisonSocial = raisonSocial;
	}

	public RefVille getVille() {
		return ville;
	}

	public void setVille(RefVille ville) {
		this.ville = ville;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntrepriseEntity(String ice, Long rc, String raisonSocial, RefVille ville, String adresse,String etat,String dateCreation) {
		super();
		this.ice = ice;
		this.rc = rc;
		this.raisonSocial = raisonSocial;
		this.ville = ville;
		this.adresse = adresse;
		this.etat=etat;
		this.dateCreation=dateCreation;
	}

	public EntrepriseEntity() {
		super();
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	

}
