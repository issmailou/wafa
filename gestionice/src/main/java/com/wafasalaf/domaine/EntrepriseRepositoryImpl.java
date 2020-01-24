//package com.wafasalaf.domaine;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.Query;
//
//import com.wafasalaf.entity.EntrepriseEntity;
//  
//public class  EntrepriseRepositoryImpl implements EntrepriseRepository {
//	 
//	EntityManager em;
//	 
//		public 	List<EntrepriseEntity> findIceByRcAndVille(Long rc,String ville) {
//	    
//	    return em.createQuery(cq).getResultList();
//	}
//
//		@Override
//		public List<EntrepriseEntity> findAll() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public List<EntrepriseEntity> findAll(Sort sort) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public List<EntrepriseEntity> findAllById(Iterable<Long> ids) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public <S extends EntrepriseEntity> List<S> saveAll(Iterable<S> entities) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public void flush() {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public <S extends EntrepriseEntity> S saveAndFlush(S entity) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public void deleteInBatch(Iterable<EntrepriseEntity> entities) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void deleteAllInBatch() {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public EntrepriseEntity getOne(Long id) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public <S extends EntrepriseEntity> List<S> findAll(Example<S> example) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public <S extends EntrepriseEntity> List<S> findAll(Example<S> example, Sort sort) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public Page<EntrepriseEntity> findAll(Pageable pageable) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public <S extends EntrepriseEntity> S save(S entity) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public Optional<EntrepriseEntity> findById(Long id) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public boolean existsById(Long id) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public long count() {
//			// TODO Auto-generated method stub
//			return 0;
//		}
//
//		@Override
//		public void deleteById(Long id) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void delete(EntrepriseEntity entity) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void deleteAll(Iterable<? extends EntrepriseEntity> entities) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void deleteAll() {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public <S extends EntrepriseEntity> Optional<S> findOne(Example<S> example) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public <S extends EntrepriseEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public <S extends EntrepriseEntity> long count(Example<S> example) {
//			// TODO Auto-generated method stub
//			return 0;
//		}
//
//		@Override
//		public <S extends EntrepriseEntity> boolean exists(Example<S> example) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public EntrepriseEntity findByRaisonSocial(String raisonSocial) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public EntrepriseEntity findByRcAndVille(Long raisonSocial, String ville) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//}