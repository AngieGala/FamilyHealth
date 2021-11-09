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
   
	@Query("from Medication m where m.medicalstaff.namePM like %:namePM%")
	List<Medication> buscarMedico(@Param("namePM") String namePM);
	
	@Query("from Medication m where m.medicinestatus.nameEDLMA like %:nameEDLMA%")
	List<Medication> buscarEstado(@Param("nameEDLMA") String nameEDLMA);
	
	@Query("from Medication m where m.medicine.nameMedicine like %:nameMedicine%")
	List<Medication> buscarMedicina(@Param("nameMedicine") String nameMedicine);
	
	@Query("from Medication m where m.patient.lastnamePatient like %:lastnamePatient%")
	List<Medication> buscarApellido(@Param("lastnamePatient") String lastnamePatient);
	
	
	List<Medication>findBydateMedication(Date dateMedication);
	
}
