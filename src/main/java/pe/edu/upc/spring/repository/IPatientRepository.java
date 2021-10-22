package pe.edu.upc.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Patient;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Integer>{
	@Query("from Patient p where p.namePatient like %:namePatient%")
	List<Patient> buscarNombre(@Param("namePatient") String namePatient);
	
	@Query("from Patient p where p.lastnamePatient like %:lastnamePatient%")
	List<Patient> buscarApellido(@Param("lastnamePatient") String lastnamePatient);
	
	@Query("from Patient p where p.dniPatient like %:dniPatient%")
	List<Patient> buscarDNI(@Param("dniPatient") String dniPatient);
	
	@Query("from Patient p where p.bednumberPatient = :bednumberPatient")
	List<Patient> buscarCama(@Param("bednumberPatient") int bednumberPatient);
	
	List<Patient> findByDatePatient(Date datePatient);
}
