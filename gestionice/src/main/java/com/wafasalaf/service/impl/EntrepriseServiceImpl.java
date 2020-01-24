package com.wafasalaf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wafasalaf.domaine.EntrepriseRepository;
import com.wafasalaf.domaine.RefVilleRepository;
import com.wafasalaf.entity.EntrepriseEntity;
import com.wafasalaf.mapper.EntrepriseBean;
import com.wafasalaf.mapper.EntrepriseMapper;
import com.wafasalaf.service.EntrepriseService;
@Service("entrepriseService")
public class EntrepriseServiceImpl implements EntrepriseService  {

	@Autowired
    private EntrepriseRepository entrepriseRepository;
	@Autowired
    private RefVilleRepository villeRepository;
	@Override
	public String chercherIce(Long rc, String ville) {
		EntrepriseEntity entreprise=entrepriseRepository.findIceByRcAndVille(rc, ville.toLowerCase());
		String ice=entreprise!=null?entreprise.getIce():null;
		return ice ;
	}
	@Override
	public Long enregistrer(EntrepriseBean bean) {
		return entrepriseRepository.save(EntrepriseMapper.map(bean)).getId();
	}
	@Override
	public Long chercherVille(String ville) {
		return villeRepository.findVilleIdByName(ville);
	}

}
