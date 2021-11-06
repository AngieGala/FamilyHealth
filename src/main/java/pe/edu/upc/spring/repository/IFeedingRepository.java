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
	
	List<Feeding>findBydateFeeding(Date dateFeeding);
}