package pe.edu.upc.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.TypeMedicalStaff;

@Repository
public interface ITypeMedicalStaffRepository extends JpaRepository<TypeMedicalStaff, Integer>{
	
}