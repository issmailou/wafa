package com.wafasalaf.domaine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wafasalaf.entity.EntrepriseEntity;
  
@Repository
public interface RefVilleRepository extends JpaRepository<EntrepriseEntity, Long> {
	@Query("select v.id from RefVille v where lower(v.libelle) like %:ville%")
	Long findVilleIdByName(String ville);
}