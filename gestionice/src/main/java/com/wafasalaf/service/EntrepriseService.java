package com.wafasalaf.service;

import com.wafasalaf.mapper.EntrepriseBean;
public interface EntrepriseService {
	
	String chercherIce(Long rc,String ville);
	
	Long enregistrer(EntrepriseBean bean);
	
	Long chercherVille(String ville);
	

}
