package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.MedicalStaff;


@Repository
public interface IMedicalStaffRepository extends JpaRepository<MedicalStaff, Integer>{
	@Query("from MedicalStaff ms where ms.typemedical.nameTDPM like %:nameTDPM%")
	List<MedicalStaff> buscartype(@Param("nameTDPM") String nameTDPM);
	
	@Query("from MedicalStaff ms where ms.namePM like %:namePM%")
	List<MedicalStaff> buscarNombre(@Param("namePM") String namePM);
	
	@Query("from MedicalStaff ms where ms.lastnamePM like %:lastnamePM%")
	List<MedicalStaff> buscarApellido(@Param("lastnamePM") String lastnamePM);
	
	@Query("from MedicalStaff ms where ms.dniPMe like %:dniPMe%")
	List<MedicalStaff> buscarDNI(@Param("dniPMe") String dniPMe);
}