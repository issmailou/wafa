package com.wafasalaf.mapper;
/**
 * 
 * @author OUARDASS ISSMAIL
 *
 */
public class EntrepriseBean  {
	
	private Long id;
	private String ice;
	private Long rc;
	private String raisonSocial;
	private Long villeId;
	private String adresse;
	private String etat;
	private String dateCreation;
	
	private int codeRetour;
	private String message;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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

	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	

	public EntrepriseBean(String ice, Long rc, String raisonSocial, Long villeId, String adresse,String etat,String dateCreation) {
		super();
		this.ice = ice;
		this.rc = rc;
		this.raisonSocial = raisonSocial;
		this.villeId = villeId;
		this.adresse = adresse;
		this.etat=etat;
		this.dateCreation=dateCreation;
	}
	public int getCodeRetour() {
		return codeRetour;
	}
	public void setCodeRetour(int codeRetour) {
		this.codeRetour = codeRetour;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public Long getVilleId() {
		return villeId;
	}
	public void setVille(Long villeId) {
		this.villeId = villeId;
	}
	public EntrepriseBean() {
		super();
	}
	


}
