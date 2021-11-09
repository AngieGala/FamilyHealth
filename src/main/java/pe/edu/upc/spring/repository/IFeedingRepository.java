package pe.edu.upc.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Feeding;

@Repository
public interface IFeedingRepository extends JpaRepository<Feeding, Integer>{
	@Query("from Feeding f where f.patient.namePatient like %:namePatient%")
	List<Feeding> buscarPaciente(@Param("namePatient") String namePatient);
	
	@Query("from Feeding f where f.medicalstaff.namePM like %:namePM%")
	List<Feeding> buscarMedico(@Param("namePM") String namePM);
	
	@Query("from Feeding f where f.patient.lastnamePatient like %:lastnamePatient%")
	List<Feeding> buscarApellido(@Param("lastnamePatient") String lastnamePatient);
	
	@Query("from Feeding f where f.feedingtype.nameTDA like %:nameTDA%")
	List<Feeding> buscarTipo(@Param("nameTDA") String nameTDA);
	
	@Query("from Feeding f where f.feedingshift.nameTRDA like %:nameTRDA%")
	List<Feeding> buscarTurno(@Param("nameTRDA") String nameTRDA);
	
	
	List<Feeding>findBydateFeeding(Date dateFeeding);
	
	
}