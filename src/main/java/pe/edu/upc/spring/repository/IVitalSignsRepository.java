package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.VitalSigns;

@Repository
public interface IVitalSignsRepository extends JpaRepository<VitalSigns, Integer>{
	@Query("from VitalSigns vs where vs.patient.namePatient like %:namePatient%")
	List<VitalSigns> buscarPaciente(@Param("namePatient") String namePatient);
	
}