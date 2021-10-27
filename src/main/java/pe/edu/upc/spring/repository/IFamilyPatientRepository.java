package pe.edu.upc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.FamilyPatient;

@Repository
public interface IFamilyPatientRepository extends JpaRepository<FamilyPatient, Integer>{

}