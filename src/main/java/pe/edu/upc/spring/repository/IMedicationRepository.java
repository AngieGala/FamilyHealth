package pe.edu.upc.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Medication;


@Repository
public interface IMedicationRepository extends JpaRepository<Medication, Integer>{
	@Query("from Medication m where m.patient.namePatient like %:namePatient%")
	List<Medication> buscarPaciente(@Param("namePatient") String namePatient);

	List<Medication>findBydateMedication(Date dateMedication);
	
}
