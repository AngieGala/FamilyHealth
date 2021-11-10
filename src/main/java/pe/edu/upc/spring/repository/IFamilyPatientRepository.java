package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.spring.model.FamilyPatient;

@Repository
public interface IFamilyPatientRepository extends JpaRepository<FamilyPatient, Integer>{

	@Query("from FamilyPatient fp where fp.namePM like %:namePM%")
	List<FamilyPatient> buscarNombre(@Param("namePM") String namePM);
	
	@Query("from FamilyPatient fp where fp.lastnameFDP like %:lastnameFDP%")
	List<FamilyPatient> buscarApellido(@Param("lastnameFDP") String lastnameFDP);
}