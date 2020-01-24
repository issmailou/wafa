package com.wafasalaf.mapper;

import org.springframework.beans.BeanUtils;

import com.wafasalaf.entity.EntrepriseEntity;
import com.wafasalaf.entity.RefVille;

public class EntrepriseMapper {

	public static EntrepriseEntity map(EntrepriseBean bean) {
		EntrepriseEntity entrepriseEntity = new EntrepriseEntity();
		BeanUtils.copyProperties( bean,entrepriseEntity);
		entrepriseEntity.setVille(new RefVille(bean.getVilleId()));
		return entrepriseEntity;

	}
}
