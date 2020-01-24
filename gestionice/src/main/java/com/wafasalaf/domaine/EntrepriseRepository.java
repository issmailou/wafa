package com.wafasalaf.domaine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wafasalaf.entity.EntrepriseEntity;
  
@Repository
public interface EntrepriseRepository extends JpaRepository<EntrepriseEntity, Long> {
	EntrepriseEntity findByRaisonSocial(String raisonSocial);
	EntrepriseEntity findByRcAndVille(Long raisonSocial,String ville);
	@Query("select e from EntrepriseEntity e where e.rc=:rc and lower(e.ville.libelle) like %:ville%")
	EntrepriseEntity findIceByRcAndVille(Long rc,String ville);
}